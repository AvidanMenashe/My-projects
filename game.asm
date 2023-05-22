; ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
; Chen Cohen Gershon 
; Avidan Menashe 
; Description: this code describes a game 
;	where the 'O' symbol needs to "eat"
;	the 'X' symbol until the user press "q" key.
; ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
.model small
.stack 100h
.data
	
	LastLoc dw 2000d				;starting location of 'O'
	Xlocation dw 0					;save the current location of 'X'
	score dw 0						;a var to save the current score of the user
	endOfGame_msg db 'Score:__$'	;msg for printing the score
	INT1C_counter dw 0d				;counter for the new int 1ch
	lastKey db 0					;saves last key that was pressed
	
.code 

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;Function: paintTheScreen
;Description : this function prints the screen in black and place the 'O' symbol
;			   in the center of the screen.
;Input: none        	    
;Output: black screen with a red 'O' in the center			 	  
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

paintTheScreen proc near uses cx bx es ax
	
	mov bx, 0h						;set bx to the start of screen
	mov cx, 80h*25h				;counter for painting each pixel in screen
	

	mov ax, 0b800h				;setting screen offset
	mov es, ax
	mov ax, 20h					;set ax to space on a black backround

continueToNext:	
	mov es:[bx] , ax			;print spce
	add bx, 2					;move bx to next position 
	sub cx, 2					; update loop counter
	cmp cx, 0					; check if we in the end of the screen
	jnz continueToNext
	
	;print 'O' =4fh
	
	mov ax, 044fh 				;set ax to a red 'O' on a black backround
	mov bx, 2000d				;new screen offset
	mov es:[bx], ax
	
ret

paintTheScreen endp 

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;Function: DetectAWSD
;Description : this function understand which key was pressed in if we are 
;			   in the edges of the screen, checks if the O eat an X and if so,
;			   generates a new X.
;Input: the scan code of the pressed key.        	    
;Output: moves 'O' to its new location, checks if an X was eaten and update the score
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

DetectAWSD proc near uses bx ax dx es si

	mov bx, [LastLoc]				;set bx to last location of 'O'


	
		in al, 60h					;get scan code of the pressed key
		and al, 7fh					;we masl the msb of the scan code


		cmp al, 1eh					;if al == 'A'- go left
		jz Apressed
		
		cmp al, 20h					;if al == 'D'- go right 
		jz Dpressed
		
		cmp al, 11h					;if al == 'W'- go up
		jz Wpressed
		
		cmp al, 1fh					;if al == 'S'- go down
		jz Spressed
		
		
		
		mov al, lastKey
		cmp al, 1eh					;if al == 'A'- go left
		jz Apressed
		
		cmp al, 20h					;if al == 'D'- go right 
		jz Dpressed
		
		cmp al, 11h					;if al == 'W'- go up
		jz Wpressed
		
		cmp al, 1fh					;if al == 'S'- go down
		jz Spressed
		
		
		jmp finish					;if no AWSD key detected
		

	Apressed:
		mov lastKey, al				;save last key scan code in case the user will tap invaild key
		mov cx, 160d				;set cx to the length of a row
		mov dx, 0					;reset dx
		mov ax, bx					;set ax to bx
		div cx 						;divide the current pos in 160 to check if we ar in the left end of the row
		cmp dx,0 					;check if the reminder is zero, thats mean that we are in the left end
		jz finish					;we stay in place if dx == 0				
		
		;if not
		mov ax, 0b800h				;setting screen offset
		mov es, ax
		mov ax, 20h					;set ax to space on a black backround 	
		mov es:[bx] , ax			;print space in last location
		sub bx, 2 					;move one position left
		mov ax, 044fh 				; set ax to red 'O'
		mov es:[bx], ax				; print 'O' in new position							
		mov [LastLoc], bx			;update the location
		jmp finish
		
		
	Dpressed:
		mov lastKey, al				;save last key scan code in case the user will tap invaild key
		mov cx, 160d				;set cx to the length of a row
		mov dx, 0					;reset dx
		mov ax, bx					;set ax to the current position
		add ax, 2					;inc the current pos by 2 (one place on screen is 2 bytes) so the current pos will be a mult of 160d and then we know if we are the the edge
		div cx 						;divide the current pos in 160 to check if we ar in the left end of the row
		cmp dx,0 					;check if the reminder is zero, thats mean that we are in the left end
		jz finish					;we stay in place if dx == 0				
		
		;if not
		mov ax, 0b800h				;setting screen offset
		mov es, ax
		mov ax, 20h					;set ax to space on a black backround 	
		mov es:[bx] , ax			;print space in last location
		add bx, 2 					;move one position left
		mov ax, 044fh 				; set ax to red 'O'
		mov es:[bx], ax				; print 'O' in new position							
		mov [LastLoc], bx			;update the location
		
		jmp finish

	Wpressed:
		mov lastKey, al				;save last key scan code in case the user will tap invaild key
		mov cx, [LastLoc]			;set cx to the current position
		cmp cx, 160d				;if the current pos is between 0-160 thats mean that we are in the first row
		jb finish					; if cx < b800h the we are no more in screen segment so we dont move
		
		;else
		mov ax, 0b800h				;screen offset
		mov es, ax
		mov ax, 20h					;set ax to space on a black backround 	
		mov es:[bx] , ax			;print space in last location
		sub bx, 160d 				;move one position up
		mov ax, 044fh 				; set ax to red 'O'
		mov es:[bx], ax				; print 'O' in new position							
		mov [LastLoc], bx			;update the location 
		
		jmp finish
		
	Spressed:
		mov lastKey, al				;save last key scan code in case the user will tap invaild key
		mov cx, [LastLoc]			;set cx to the current position
		cmp cx, 3839d				;if cx is bigger the the value of the start of the last row we dont go down
		ja finish
		
		;else
		mov ax, 0b800h				;screen offset
		mov es, ax
		mov ax, 20h					;set ax to space on a black backround 	
		mov es:[bx] , ax			;print space in last location
		add bx, 160d 				;move one position down
		mov ax, 044fh 				; set ax to red 'O'
		mov es:[bx], ax				; print 'O' in new position							
		mov [LastLoc], bx			;update the location 
		
		jmp finish
		
finish:

	cmp bx,[Xlocation]			;we check if the 'O' and the 'X' are in the same location
	jz CreateNewTarget			;if they are we jump to lable "CreateNewTarget"
	jmp O_didnt_eat_X			;if they dont, we return 


CreateNewTarget:

	call CreateXonScreen			;we call the function the randomliy create a new 'X' on the screen
	mov si,offset score				;si now points on the current players score value
	add ds:[si], 1					;inc the score by 1 because the player got the 'X' 
			
O_didnt_eat_X:

	ret

DetectAWSD endp
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;Function: CreateXonScreen
;Description : this function uses ports 70,71 to read the seconds and minutes values from clock 
;			   and saves the seconds in the higher byte of bx and minutes in the low one and
;			   and calls Fix_X_location func to make it a vaild screen offset.
;Input: second and minutes values from the clock.        	    
;Output: prints an X on screen in random place and saves the new location in memory.
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
CreateXonScreen proc near uses ax bx si es
	
	mov si, offset Xlocation	; si points on the first byte of Xlocation

TryAgain:
	
	mov al , 2					;service 02 - return from RTC the minutes val
	out 70h, al					;write request 4 to port 70h
	in al, 71h					;read the value
	
	mov bl,al					;save minutes in the low byte
	
	mov al, 0					;service 00 - return from RTC the seconds val
	out 70h, al					;ask for data 
	in al, 71h 					;get answer form device (format-BCD)
	
	mov bh,al					;save seconds in the higher byte
	
	;code for converting bx to a vaild screen offset
	call Fix_X_location
	
	cmp bx, 2000d				;check if 'X' position is on the middel of the screen 
	jz TryAgain
	
	cmp bx, [Xlocation]			;check if we got the same location for 'X'
	jz TryAgain
	
	mov ds:[si], bx				;update Xlocation
	
	;printing:
	mov ax, 0b800h				;screen offset
	mov es, ax
	mov ax, 0458h				;value for 'X' on red backround
	mov es:[bx], ax				;print 'X' on the location got for the RTC

	mov [Xlocation], bx
	ret

CreateXonScreen endp

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;Function: Fix_X_location 
;			   This function takes the value saved in bx and clac
;			   (bx mod 2000)*2 to get a vaild-random screen offset for printing an X.
;Input: bx value    	    
;Output: vaild screen offset to use for the print saved in bx
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Fix_X_location proc near uses cx si ax dx 
								
	
	mov ax, bx							;set ax to the clock value
	mov si, 2000d						;we divide ax by mod2000 because the reminder can only be between 0-1999 
										;and this will give us a vaild screen offset
	
	div si								; currentXposition / 2000
	sal dx,1							; dx * 2
	mov bx, dx							; update X position
	
	ret					

Fix_X_location endp

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;Function: PrintScore
;Description : this function prints a messege to the screen with the score of the
;			   user in the end of the game using int 21h. the score of the user is 
;			   saved in memory in a variable.
;Input: the score of the user.        	    
;Output: print the score of the user on the screen .
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
PrintScore proc near uses ax bx si es dx
	
	;use int 10h  to set the screen curser for the print the end of game msg
	mov dh, 0
	mov dl, 0
	mov bh, 0
	mov ah, 2
	int 10h
	
	;print the msg using int 21h
	mov dx, offset endOfGame_msg
	mov ah, 9h
	int 21h
	
	
	mov si, offset score				;si points on the score adress
	mov ax, ds:[si]						;move the value of score to ax
	mov bx, 10d							
	xor dx, dx							;set dx to zero for the division
	div bx								;divid dx:ax by 10 so that ax has the higher digit and dx the lower one	
	add ax, 30h							;add 30h to get the ascii value
	add dx, 30h							;add 30h to get the ascii value					

	
	mov bx, 12d							;set bx for the screen offset bytes 12,14
	mov cx, 0b800h						;set screen offset
	mov es, cx
	mov ah, 7h							;set ah for the backround color of the print
	mov dh, 7h							;set dh for the backround color of the print
	mov es:[bx],ax						;print higher digit of score
	add bx, 2							;inc bx to byte 14 for next digit
	mov es:[bx],dx						;print lower digit of score
	
	
	;paint in black X,O
	mov si,[LastLoc]
	mov cx,0020h 						;black color ang background
	mov es:[si],cx
	mov si,[Xlocation]
	mov es:[si],cx



	;set curser in the start of the next line
	mov dh, 1
	mov dl, 0
	mov bh, 0
	mov ah, 2
	int 10h

	ret
PrintScore endp

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;Function: ISR_new_INT_1Ch
;Description : this function is an upgrade for int 1ch, and we use it to generate a delay 
;			   of 155msec between two moves of the 'O' symbol on the screen and the call the 
;			   old int 1ch located now in int 80h. 
;			   we count until 3 and only then enable a movment, in the mean time we execute int 80h regulary
;Input: none        	    
;Output: none
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

ISR_new_INT_1Ch proc far 
	
	cmp INT1C_counter,3		;check if counter == 3 ?
	jnz RepeatCount			;if taken, we inc the counter
	
	mov INT1C_counter, 0	;if counter == 3, set it to 0 for next count
	call DetectAWSD			;counter is 3 so we can move the player now
	jmp continueINT			;now we dont inc the counter, we just want to call int 80h
	
RepeatCount:	
	inc INT1C_counter		;inc the counter because its not equal to 3
	
continueINT:
	;call old int 1ch	
	int 80h
	iret
	
ISR_new_INT_1Ch endp

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~MAIN~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	;set data segment
	.startup
	
	mov ax, 0
	mov es, ax						;set ex on adrees 00000h

	cli								; block interrupts 
	;moving Int 1ch into IVT[080h]:
	
	mov ax,es:[1ch*4]				;copying old ISR1C IP to free vector 
	mov es:[80h*4],ax 
	mov ax,es:[1ch*4+2]				;copying old ISR1c CS to free vector 
	mov es:[80h*4+2],ax 
	mov ax, offset ISR_new_INT_1Ch 	;copying IP of ISR_New_1ch to IVT[1c]
	mov es:[1ch*4],ax 
	mov ax,cs						;copying CS of our ISR_New_1ch IVT[1c] 
	mov es:[1ch*4+2],ax 
	sti								;enable interrupts
	
	
	;mask int 9
	cli
	in  al, 21h 
	or  al, 02h 
	out 21h, al 
	sti
	
	;preper the screen for the game
	call paintTheScreen
	
	;create the first 'X' on screen
	call CreateXonScreen
	
pollKeyboard:	
	;we make a pool on the keyboard to detect when a new key is pressed
	in al, 64h
	test al, 1
	jz pollKeyboard
	
	;check if user wants to exit:
	in al, 60h						;read port 60h
	cmp al, 90h 					;if al == 'Q' then quit the game, we use the scan code of a relased 'Q' key
	jz Quit
	
	jmp pollKeyboard
	
Quit:
	call PrintScore
	
	mov ax, 0							;set es to address 00000h
	mov es, ax
	cli									; block interrupts 
	;moving old_Int1c back into IVT[1ch] :
	
	mov ax,es:[80h*4]					;copying old ISR1C IP to free vector 
	mov es:[1ch*4],ax 
	mov ax,es:[80h*4+2]					;copying old ISR9 CS to free vector 
	mov es:[1ch*4+2],ax 
	sti									;enable interrupts
	
	
	;unmask int 9
	cli
	mov al, 0
	out 21h, al
	sti
	
	;return to os
	.exit
	
end




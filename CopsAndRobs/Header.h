#pragma once

#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>
#include<math.h>
#include<string.h>
#include <stdlib.h>
#include <conio.h>
#define MAX_SIZE 30
#define MIN_SIZE 5
#define MAX_NUM_OF_COPS 5
#define MAX_NUM_OF_MOVES 30
#define SIZE_OF_GAME_TABLE 3

struct Position
{
    short row;
    short col;
};

/*************************************************************************
Function name: ourApp
Input: no input
Output: no output
The function operation: executing number of functions from this function in order to execute a game.
***************************************************************************/
void ourApp();

/*************************************************************************
Function name: printNames
Input: two strings
Output: no output
The function operation: the fuction prints the two input strings
***************************************************************************/
void printNames(char firstName[10], char lastName[10]);

/*************************************************************************
Function name: num_of_rows
Input: no input
Output: integer
The function operation: the fuction canculates the number of rows for game board
***************************************************************************/
int num_of_rows();

/*************************************************************************
Function name: num_of_cols
Input: the number of rows from "num_of_rows" function (integer)
Output: integer
The function operation: the fuction canculates the number of rows for game board
***************************************************************************/
int num_of_cols(int numOfrow);

/*************************************************************************
Function name: intiGameTable
Input:  game table dimantions (two integers) and unsigned char game table
Output: no output
The function operation: the function initialize the game table with '-'
***************************************************************************/
void initGameTable(int numOfrow, int numOfcol, unsigned char GameTable[MAX_SIZE][MAX_SIZE]);

/*************************************************************************
Function name: CopsAndRobs
Input: game table dimantions (two integers)
Output: no output
The function operation: implemention of cops and robs game
***************************************************************************/
void CopsAndRobs(const int numOfrow, const int numOfcol);

/*************************************************************************
Function name: CopsMoves
Input: game table dimantions, game table dimantions, struct for rob position and struct for chosen cops position
Output: an integer flag that indicates if game is over
The function operation: canculating the possible movements and printing it to the user
***************************************************************************/
int CopsMoves(unsigned char GameTable[MAX_SIZE][MAX_SIZE], int NumOfRows, int NumOfCol, int NumOfCops, Position RobPos, Position arrCopsPos[MAX_NUM_OF_COPS]);

/*************************************************************************
Function name: calc_mid_index
Input:integer value
Output: middle index value
The function operation: canculation middle row/colum index for printing 'R' in game table
***************************************************************************/
int calc_mid_index(int value);


/*************************************************************************
Function name: initCopsPos
Input: number of cops (int), struct for cops position, the game table, num of rows and num of cols
Output: no output
The function operation: the user choose where he want to put the cops in the game table and the function preform it
***************************************************************************/
void initCopsPos(int numOfRows, int numOfCols, int NumOfcops, Position arrCopsPos[MAX_NUM_OF_COPS], unsigned char GameTable[MAX_SIZE][MAX_SIZE]);

/*************************************************************************
Function name: PrintGameBoard
Input: game table dimantions, game table dimantions and game table
Output: no output
The function operation: printing the board game on the screen
***************************************************************************/
void PrintGameBoard(unsigned char GameTable[MAX_SIZE][MAX_SIZE], int NumOfRows, int NumOfCol);

/*************************************************************************
Function name: CopsTurn
Input: the game table, game table sizes, struct of rob position and struct for cops position
Output: no output
The function operation: getting position from user for movement of cops in the game table
***************************************************************************/
void CopsTurn(unsigned char GameTable[MAX_SIZE][MAX_SIZE], int NumOfRows, int NumOfCol, int NumOfCops, Position RobPos, Position arrCopsPos[MAX_NUM_OF_COPS]);

/*************************************************************************
Function name: RobMoves
Input: the game table, game table dimantions, struct for cops position, number of cops in game, struct for robs position
Output: no output
The function operation: canculates the nearest cops index
***************************************************************************/
void RobMoves(unsigned char GameTable[MAX_SIZE][MAX_SIZE], int NumOfRows, int NumOfCol, Position arrCopsPos[MAX_NUM_OF_COPS], int NumOfCops, Position RobPos);

/*************************************************************************
Function name: ChooseRobMove
Input: the game table, game table dimantions, struct for cops position, index of nearest cop, struct for robs
Output: no output
The function operation: the funcion decides where the robber is going to move depending one which moves he can preforme and if he cant it dont move
***************************************************************************/
void ChooseRobMove(unsigned char GameTable[MAX_SIZE][MAX_SIZE], int NumOfRows, int NumOfCol, Position arrCopsPos[MAX_NUM_OF_COPS], Position RobPos, int MinCopIndex);

/*************************************************************************
Function name: GetRobPos
Input: game table, game table dimantions
Output: struct of type position
The function operation: the fuction runs on game table and when it findes the robber postion , it saves it into the struct
***************************************************************************/
Position GetRobPos(unsigned char GameTable[MAX_SIZE][MAX_SIZE], int NumOfRows, int NumOfCol);

/*************************************************************************
Function name: CaseForOneCop
Input: number of cops, struct of type positin for robber postion , struct of type position for cops positions, the game table, game tabke dimantions,
Output: return an integer flag to determine if game is over
The function operation: the funcion handle the special case for one cop only
***************************************************************************/
int CaseForOneCop(int NumOfCops, Position RobPos, Position arrCopsPos[MAX_NUM_OF_COPS], unsigned char GameTable[MAX_SIZE][MAX_SIZE], int NumOfRows, int NumOfCol);

/*************************************************************************
Function name: StringsScanningAndPrinting
Input: char arrays for first and last name of user
Output: no output
The function operation: the fuction scans two strings and print them using printNames functions
***************************************************************************/
void StringsScanningAndPrinting(char firstName[10], char lastName[10]);


/*************************************************************************
Function name: EndingPrints
Input: char arrays for first and last name of user
Output: no output
The function operation: the fuction print the ending of program prints using printNames functions
***************************************************************************/
void EndingPrints(char firstName[10], char lastName[10]);


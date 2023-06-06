#include "Header.h"


void StringsScanningAndPrinting(char firstName[10], char lastName[10]) {
    //scanning the user name:
    printf("Hello user!, Please enter your first and last name:\n");
    scanf("%s %s", firstName, lastName);
    printf("Hello ");
    printNames(firstName, lastName);
    printf(", welcome to our app!\n");
}

void EndingPrints(char firstName[10], char lastName[10]) {
    //if user choose to exit:
    printf("Bye, ");
    printNames(firstName, lastName);
    printf(".\n");

}


//ourApp function:
void ourApp() {
    static char firstName[10], lastName[10];
    StringsScanningAndPrinting(firstName, lastName);

    int numOfrow = num_of_rows();
    int numOfcol = num_of_cols(numOfrow);

    char user_choice = 0;
    do {
        printf("Do you want to play [y/n]?\n");
        //putting a whitespace before %c because it takes the \n as a char
        scanf(" %c", &user_choice);
        if (user_choice == 'y')
        {
            CopsAndRobs(numOfrow, numOfcol);
            //ask the user to press any key to continue:
            printf("Press any key to continue\n");
            //wait for key
            _getch();
            //clear consol
            system("cls");
        }
    } while ('y' == user_choice);
    EndingPrints(firstName, lastName);
}

void CopsAndRobs(const int NumOfRows, const int NumOfCol) {

    unsigned char GameTable[MAX_SIZE][MAX_SIZE];
    //initializing the game table:
    initGameTable(NumOfRows, NumOfCol, GameTable);


    int NumOfCops = 0;
    printf("How many cops do you want to insert (1-5)?\n");
    scanf("%d", &NumOfCops);

    //chencking if the number of cops is valid:
    while (NumOfCops < 1 || NumOfCops > 5)
    {
		printf("Invalid number of cops, please enter a number between 1-5:\n");
		scanf("%d", &NumOfCops);
	}

    //placing the robber in the middel of the table
    int row_mid = calc_mid_index(NumOfRows);
    int col_mid = calc_mid_index(NumOfCol);
    GameTable[row_mid][col_mid] = 'R';


    //placing the cops:
    //2D array for saving cops intial position
    Position arrCopsPos[MAX_NUM_OF_COPS] = { {0,0} };
    Position RobPos;
    RobPos.row = row_mid;
    RobPos.col = col_mid;
    initCopsPos(NumOfRows, NumOfCol, NumOfCops, arrCopsPos, GameTable);

    printf("Well, Let's play!\n");
    printf("Initial states:\n");
    //printing the game table:
    PrintGameBoard(GameTable, NumOfRows, NumOfCol);

    int isGameOver = 0;
    for (short i = 0; i < MAX_NUM_OF_MOVES; i++)
    {
    isGameOver = CaseForOneCop(NumOfCops, RobPos, arrCopsPos, GameTable, NumOfRows, NumOfCol);
        if (isGameOver == 1)
        {
            break;
        }
        printf("Cops vaild movements for this turn, please choose in the format of - val val:\n");
        RobPos = GetRobPos(GameTable, NumOfRows, NumOfCol);
        isGameOver = CopsMoves(GameTable, NumOfRows, NumOfCol, NumOfCops, RobPos, arrCopsPos);
        if (isGameOver == 1)
        {
            break;
        }
        int CurRow = 0, CurCol = 0;
        int NewRow = 0, NewCol = 0;
        scanf("%d %d", &CurRow, &CurCol);
        scanf("%d %d", &NewRow, &NewCol);
        GameTable[CurRow][CurCol] = '-';
        //if the cop caught the robber:
        if (GameTable[NewRow][NewCol] == 'R')
        {
            //printing game board after cops turn:
            GameTable[NewRow][NewCol] = 'C';
            PrintGameBoard(GameTable, NumOfRows, NumOfCol);
            //printing the winner:
            printf("The cops won!\n");
            break;
        }
        GameTable[NewRow][NewCol] = 'C';
        //printing game board after cops turn:
        PrintGameBoard(GameTable, NumOfRows, NumOfCol);
        //updating chosen cop position
        for (int i = 0; i < NumOfCops; i++)
        {
            if (arrCopsPos[i].row == CurRow && arrCopsPos[i].col == CurCol)
            {
                arrCopsPos[i].row = NewRow;
                arrCopsPos[i].col = NewCol;

            }
        }
        printf("Rob:\n");
        RobMoves(GameTable, NumOfRows, NumOfCol, arrCopsPos, NumOfCops, RobPos);

        //if 30 turns where made and the cops didnt won:
        if (i == (MAX_NUM_OF_MOVES - 1))
        {
            printf("The rob managed to escape!\n");
        }
    }
}

void initGameTable(int numOfrow, int numOfcol, unsigned char GameTable[][MAX_SIZE]) {

    //filling game table:
    for (int i = 0; i < numOfrow; i++) {
        for (int j = 0; j < numOfcol; j++) {
            GameTable[i][j] = '-';
        }
    }

}

void printNames(char firstName[10], char lastName[10]) {
    printf("%s ", firstName);
    printf("%s", lastName);
}

int num_of_rows() {
    int numOfrow;
    printf("Let's choose the size of the board, rows?:\n");
    scanf("%d", &numOfrow);

    //Chacking the size of the number of rows:
    if (numOfrow > MAX_SIZE) {
        numOfrow = MAX_SIZE;
    }
    else if (numOfrow < MIN_SIZE) {
        numOfrow = MIN_SIZE;
    }

    return numOfrow;
}

int num_of_cols(int numOfrow) {
    int numOfcol;
    printf("cols?:\n");
    scanf("%d", &numOfcol);

    //Chacking the size of the number of colomus:
    if (numOfcol == -1) {
        numOfcol = numOfrow;
    }
    else {
        if (numOfcol > MAX_SIZE) {
            numOfcol = MAX_SIZE;
        }
        else if (numOfcol < MIN_SIZE) {
            numOfcol = MIN_SIZE;
        }
    }

    return numOfcol;
}

int calc_mid_index(int value) {
    int middle = 0;

    //if value num is even:
    if ((value % 2) == 0) {
        middle = (value / 2) - 1;
    }
    //if its odd:
    else {
        middle = (value / 2);
    }
    return middle;
}

void initCopsPos(int numOfRows, int numOfCols, int NumOfcops, Position arrCopsPos[MAX_NUM_OF_COPS], unsigned char GameTable[MAX_SIZE][MAX_SIZE])
{

    printf("Let's choose a cell for the cop:\n");
    printf("(you need to insert a number for the rows index and another for the col index)\n");
    printf("The indexes need to be between 0 to %d for the rows and 0 to %d for the cols\n", numOfRows - 1, numOfCols - 1);

    for (int i = 0; i < NumOfcops; i++) {
        
        int row_choice, col_choice;
        scanf("%d %d", &row_choice, &col_choice);

        //chacking if the location is taken:
        //if it does , ask for indxes again until user choose empty spot
        while ((GameTable[row_choice][col_choice] == 'R') || (GameTable[row_choice][col_choice] == 'C')) {
            printf("Place taken, choose another one:\n");
            scanf("%d %d", &row_choice, &col_choice);
        }
        //a flag to move between rows of cops_pos:
        GameTable[row_choice][col_choice] = 'C';
        arrCopsPos[i].row = row_choice;
        arrCopsPos[i].col = col_choice;

    }
}

void PrintGameBoard(unsigned char GameTable[MAX_SIZE][MAX_SIZE], int NumOfRows, int NumOfCol)
{
    for (int count_1 = 0; count_1 < NumOfRows; count_1++) {
        for (int count_2 = 0; count_2 < NumOfCol; count_2++) {
            printf("%c", GameTable[count_1][count_2]);
        }
        printf("\n");
    }
}

int CopsMoves(unsigned char GameTable[MAX_SIZE][MAX_SIZE], int NumOfRows, int NumOfCol, int NumOfCops, Position RobPos, Position arrCopsPos[MAX_NUM_OF_COPS]) {
    int isGameOver = 0;
    //special case, if theres only one cop:
    if (NumOfCops == 1)
    {
        int SumOfPositions = RobPos.row + RobPos.col + arrCopsPos[0].row + arrCopsPos[0].col;
        //chacking if this sum is even
        //if it is, game over, robber won.
        if ((SumOfPositions % 2) == 0)
        {
            PrintGameBoard(GameTable, NumOfRows, NumOfCol);
            printf("The rob managed to escape!\n");
            isGameOver = 1;
            return isGameOver;
        }
    }

    for (int i = 0; i < NumOfRows; i++) {
        for (int j = 0; j < NumOfCol; j++) {
            if (GameTable[i][j] == 'C')
            {
                //case for down
                if ((i + 1) < NumOfRows)
                {
                    if (!(GameTable[i + 1][j] == 'C'))
                    {
                        printf("[%d,%d] -> [%d,%d]\n", i, j, (i + 1), j);
                    }

                }
                //case for up
                if ((i - 1) >= 0)
                {
                    if (!(GameTable[i - 1][j] == 'C'))
                    {
                        printf("[%d,%d] -> [%d,%d]\n", i, j, (i - 1), j);
                    }

                }
                //case for right
                if ((j + 1) < NumOfCol)
                {
                    if (!(GameTable[i][j + 1] == 'C'))
                    {
                        printf("[%d,%d] -> [%d,%d]\n", i, j, i, (j + 1));
                    }

                }
                //case for left
                if ((j - 1) >= 0)
                {
                    if (!(GameTable[i][j - 1] == 'C'))
                    {
                        printf("[%d,%d] -> [%d,%d]\n", i, j, i, (j - 1));
                    }

                }

            }
        }
    }
    return isGameOver;

}

void CopsTurn(unsigned char GameTable[MAX_SIZE][MAX_SIZE], int NumOfRows, int NumOfCol, int NumOfCops, Position RobPos, Position arrCopsPos[MAX_NUM_OF_COPS]) {

    printf("Cops:\n");
    CopsMoves(GameTable, NumOfRows, NumOfCol, NumOfCops, RobPos, arrCopsPos);
    int CurRow = 0, CurCol = 0;
    int NewRow = 0, NewCol = 0;
    scanf("%d %d", &CurRow, &CurCol);
    scanf("%d %d", &NewRow, &NewCol);
    GameTable[CurRow][CurCol] = '-';
    if (GameTable[NewRow][NewCol] == 'R')
    {
        GameTable[NewRow][NewCol] = 'C';
        PrintGameBoard(GameTable, NumOfRows, NumOfCol);
        printf("The cops won!\n");
        return;

    }
    GameTable[NewRow][NewCol] = 'C';
}


void RobMoves(unsigned char GameTable[MAX_SIZE][MAX_SIZE], int NumOfRows, int NumOfCol, Position arrCopsPos[MAX_NUM_OF_COPS], int NumOfCops, Position RobPos) {


    float RobCopsDisntance[MAX_NUM_OF_COPS] = { 0 };

    //canculating distances of all cops from robber: 
    for (int i = 0; i < NumOfCops; i++)
    {
        RobCopsDisntance[i] = sqrtf((powf(float((arrCopsPos[i].row - RobPos.row)), 2)) + (powf(float((arrCopsPos[i].col - RobPos.col)), 2)));
    }
    //running on distance array and choosing the smallest one:
    float TempDistance = RobCopsDisntance[0];
    int MinDisIndex = 0;
    for (int i = 1; i < NumOfCops; i++)
    {
        if (TempDistance > RobCopsDisntance[i])
        {
            TempDistance = RobCopsDisntance[i];
            MinDisIndex = i;
        }
        else if (TempDistance == RobCopsDisntance[i])
        {
            if (arrCopsPos[i].row < arrCopsPos[MinDisIndex].row)
            {
                MinDisIndex = i;
            }
            else if (arrCopsPos[i].row == arrCopsPos[MinDisIndex].row)
            {
                if (arrCopsPos[i].col < arrCopsPos[MinDisIndex].col)
                {
                    MinDisIndex = i;
                }
            }
        }
    }
    ChooseRobMove(GameTable, NumOfRows, NumOfCol, arrCopsPos, RobPos, MinDisIndex);
    PrintGameBoard(GameTable, NumOfRows, NumOfCol);

}


void ChooseRobMove(unsigned char GameTable[MAX_SIZE][MAX_SIZE], int NumOfRows, int NumOfCol, Position arrCopsPos[MAX_NUM_OF_COPS], Position RobPos, int MinCopIndex) {

    //if cop is higher then rob:
    if (arrCopsPos[MinCopIndex].row < RobPos.row)
    {
        //rob goes down
        //chacking limit of table
        if ((RobPos.row + 1) < NumOfRows)
        {
            //if this sell is empty:
            if (GameTable[RobPos.row + 1][RobPos.col] == '-')
            {
                GameTable[RobPos.row][RobPos.col] = '-';
                GameTable[RobPos.row + 1][RobPos.col] = 'R';
            }
        }
    }
    //if cop is lower then rob:
    else if (arrCopsPos[MinCopIndex].row > RobPos.row)
    {
        //rob goes up
        //checking limit of table
        if ((RobPos.row - 1) >= 0)
        {
            if (GameTable[RobPos.row - 1][RobPos.col] == '-')
            {
                GameTable[RobPos.row][RobPos.col] = '-';
                // RobPos->row = RobPos->row - 1;
                GameTable[RobPos.row - 1][RobPos.col] = 'R';
            }
        }
    }
    //if they are in the same row
    else
    {
        //rob goes right
        if (arrCopsPos[MinCopIndex].col < RobPos.col)
        {
            //chacking limit of table
            if ((RobPos.col + 1) < NumOfCol)
            {
                if (GameTable[RobPos.row][RobPos.col + 1] == '-')
                {
                    GameTable[RobPos.row][RobPos.col] = '-';
                    //RobPos->col = RobPos->col + 1;
                    GameTable[RobPos.row][RobPos.col + 1] = 'R';
                }

            }
        }
        //rob goes left
        else if (arrCopsPos[MinCopIndex].col > RobPos.col)
        {
            //chacking limit of table
            if ((RobPos.col + 1) >= 0)
            {
                if (GameTable[RobPos.row][RobPos.col - 1] == '-')
                {
                    GameTable[RobPos.row][RobPos.col] = '-';
                    // RobPos->col = RobPos->col - 1;
                    GameTable[RobPos.row][RobPos.col - 1] = 'R';
                }

            }

        }


    }

}

Position GetRobPos(unsigned char GameTable[MAX_SIZE][MAX_SIZE], int NumOfRows, int NumOfCol) {
    //finding current position of robber:
    Position RobsPos = { 0 };
    for (int i = 0; i < NumOfRows; i++) {
        for (int j = 0; j < NumOfCol; j++) {
            if (GameTable[i][j] == 'R')
            {
                RobsPos.row = i;
                RobsPos.col = j;
            }
        }
    }
    return RobsPos;
}

int CaseForOneCop(int NumOfCops, Position RobPos, Position arrCopsPos[MAX_NUM_OF_COPS], unsigned char GameTable[MAX_SIZE][MAX_SIZE], int NumOfRows, int NumOfCol) {
    int isGameOver = 0;
    //special case, if theres only one cop:
    if (NumOfCops == 1)
    {
        int SumOfPositions = RobPos.row + RobPos.col + arrCopsPos[0].row + arrCopsPos[0].col;
        //chacking if this sum is even
        //if it is, game over, robber won.
        if ((SumOfPositions % 2) == 0)
        {
            //PrintGameBoard(GameTable, NumOfRows, NumOfCol);
            printf("The rob managed to escape!\n");
            isGameOver = 1;
            return isGameOver;
        }
    }
    return isGameOver;
}

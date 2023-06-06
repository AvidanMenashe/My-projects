# Author : Avidan Menashe
# Description : This code reads from an input file and simulates an OS-Timer
# our code will calculate the TurnAround time for each Timeing-protocol
import sys
import numpy as np
import tqdm as tqdm
from ProcessClass import ProcessParameters

# create a global clock variable:
clock = 0
# create a list of mean turn around time for each algorithm
meanTurnAroundTime = []


def readDataFromFile(path):
    """
    This function reads form an input file given from the command line
    :param path: The path of the input file given in the terminal
    :return: a list with the content of the file
    """
    # Open the file using the path given in the command line
    file = open(path, "r")
    # Read the file and save it in a list
    data = file.readlines()
    data = [s.replace("\n", "") for s in data]
    # covert each string to list of integers
    numOfProcess = int(data[0])
    data.remove(data[0])
    data = [list(map(int, s.split(","))) for s in data]

    # Close the file
    file.close()

    return numOfProcess, data


# ----------------------------------------------------------------------

def createObjectsForProcesses(data, numOfProcess):
    """
    This function creates a list of objects for each process
    :param data: list of lists, each sublist holds arrival time and computation time
    :param numOfProcess: the number of processes
    :return: list of process object's
    """
    processList = []
    for i in range(numOfProcess):
        processList.append(ProcessParameters(data[i], i + 1))
        # processList[i].printProcess()

    return processList


def FCFS(processList):
    """
    This function simulates the FCFS (First-Comes First-Serve) algorithm
    :param processList:
    :return: None
    """
    # Import the global clock variable
    global clock, meanTurnAroundTime

    # Sort the list by the arrival time
    # if the arrival time is the same, sort by the sub priority!
    processList.sort(key=lambda obj: (obj.ArrivalTime, obj.SubPriority))

    # start running the i'th process:
    for i in tqdm.tqdm(range(len(processList))):
        # check if the process has already terminated
        if not processList[i].isTerminated:
            if clock < processList[i].ArrivalTime:
                clock = processList[i].ArrivalTime
            # simulate the computation time of the process
            for j in range(processList[i].ComputationTime):
                clock += 1
            # process finished running
            processList[i].isTerminated = True
            # calculate the turn around time
            processList[i].TurnAroundTime = clock - processList[i].ArrivalTime

    # calculate the mean turn around time of FCFS:
    meanTurnAroundTime.append(np.mean([processList[i].TurnAroundTime for i in range(len(processList))]))


# ----------------------------------------------------------------------

def LCFS_NP(processList):
    """
    This function simulates the LCFS non-preemptive (Last-Comes First-Serve) algorithm
    :param processList: list of process object's
    :return: None
    """
    global clock, meanTurnAroundTime
    # reset the clock
    clock = 0

    # reset isTerminated for all the processes
    for i in range(len(processList)):
        processList[i].isTerminated = False

    # Sort the list by the arrival time
    # if the arrival time is the same, sort by the sub priority!
    processList.sort(key=lambda obj: (obj.ArrivalTime, obj.SubPriority))

    # set index variable and list:
    index = 0
    indexList = []

    # set the clock to the first process arrival time
    clock = processList[0].ArrivalTime

    # start running the i'th process:
    while not all(processList[i].isTerminated for i in range(len(processList))):
        # reset index list
        indexList = []
        for num in range(len(processList)):
            # check if the process has already terminated
            if not processList[num].isTerminated:
                # check the index of the max arrival time the is less or equal to the clock
                if processList[num].ArrivalTime <= clock:
                    indexList.append(num)
        # check if there is no process that arrived yet:
        if len(indexList) == 0:
            clock += 1
        else:
            # update the new index, the max index in the list is the index of the with the max arrival time
            # that is less or equal to the clock
            index = max(indexList)

            # simulate the computation time of the process
            for j in range(processList[index].ComputationTime):
                clock += 1
            # process finished running
            processList[index].isTerminated = True
            # calculate the turn around time
            processList[index].TurnAroundTime = clock - processList[index].ArrivalTime

    # calculate the mean turn around time of LCFS (NP):
    meanTurnAroundTime.append(np.mean([processList[i].TurnAroundTime for i in range(len(processList))]))


# ----------------------------------------------------------------------

def LCFS_P(processList):
    """
    This function simulates the LCFS preemptive (Last-Comes First-Serve) algorithm
    :param processList:
    :return: none
    """
    global clock, meanTurnAroundTime
    # reset the clock
    clock = 0

    # reset isTerminated for all the processes
    for i in range(len(processList)):
        processList[i].isTerminated = False

    # Sort the list by the arrival time
    # if the arrival time is the same, sort by the sub priority!
    processList.sort(key=lambda obj: (obj.ArrivalTime, obj.SubPriority))

    # set index variable and list:
    index = 0
    indexList = []

    # set the clock to the first process arrival time
    clock = processList[0].ArrivalTime

    while not all(processList[i].isTerminated for i in range(len(processList))):
        # reset index list
        indexList = []
        for num in range(len(processList)):
            # check if the process has already terminated
            if not processList[num].isTerminated:
                # check the index of the max arrival time the is less or equal to the clock
                if processList[num].ArrivalTime <= clock:
                    indexList.append(num)
        # check if there is no process that arrived yet:
        if len(indexList) == 0:
            clock += 1
        else:
            # update the new index, the max index in the list is the index of the with the max arrival time
            # that is less or equal to the clock
            index = max(indexList)


            # check if computation time is 0:
            if processList[index].ComputationTime == 0:
                processList[index].isTerminated = True
                # calc Turnaround:
                processList[index].TurnAroundTime = clock - processList[index].ArrivalTime

            else:
                # simulate the computation time of the process
                clock += 1
                processList[index].RemainingTime -= 1

                # check if the process finished running
                if processList[index].RemainingTime <= 0:
                    # process finished running
                    processList[index].isTerminated = True
                    # calculate the turn around time
                    processList[index].TurnAroundTime = clock - processList[index].ArrivalTime

    # calculate the mean turn around time of LCFS (P):
    meanTurnAroundTime.append(np.mean([processList[i].TurnAroundTime for i in range(len(processList))]))

    # reset the RemainingTime for all the processes
    for i in range(len(processList)):
        processList[i].resetRemainingTime()


# ----------------------------------------------------------------------

def SJF_P(processList):
    """
    This function simulates the SJF preemptive (Shortest Job First) algorithm
    :param processList:
    :return: none
    """
    global clock, meanTurnAroundTime
    # reset the clock
    clock = 0

    # reset isTerminated for all the processes
    for i in range(len(processList)):
        processList[i].isTerminated = False

    # Sort the list by the arrival time
    # if the arrival time is the same, sort by the sub priority!
    processList.sort(key=lambda obj: (obj.ArrivalTime, obj.SubPriority))

    # set index variable and list:
    index = 0

    # set the clock to the first process arrival time
    clock = processList[0].ArrivalTime

    while not all(processList[i].isTerminated for i in range(len(processList))):

        # Reset the index list
        indexList = []
        for num in range(len(processList)):
            # check if the process has already terminated
            if not processList[num].isTerminated:
                # check the index of the max arrival time the is less or equal to the clock
                if processList[num].ArrivalTime <= clock:
                    indexList.append(num)
        # choose the process with the min computation time
        if len(indexList) != 0:
            index = min(indexList, key=lambda ind: processList[ind].ComputationTime)

            # check if computation time is 0:
            if processList[index].ComputationTime == 0:
                processList[index].isTerminated = True
                # calc Turnaround:
                processList[index].TurnAroundTime = clock - processList[index].ArrivalTime
            else:
                # Increment the clock
                clock += 1
                # Decrement the Remaining time:
                processList[index].RemainingTime -= 1
                # check if the process finished running
                if processList[index].RemainingTime == 0:
                    # process finished running
                    processList[index].isTerminated = True
                    # calculate the turn around time
                    processList[index].TurnAroundTime = clock - processList[index].ArrivalTime
        else:
            clock += 1

    # calculate the mean turn around time of SJF (P):
    meanTurnAroundTime.append(np.mean([processList[i].TurnAroundTime for i in range(len(processList))]))
    # reset the RemainingTime for all the processes
    for i in range(len(processList)):
        processList[i].resetRemainingTime()


# ----------------------------------------------------------------------

def RR(processList, quantum):
    """
    This function simulates the Round Robin algorithm
    :param processList: the list of processes
    :param quantum: the quantum time which is the time slice
    :return: none
    """

    global clock, meanTurnAroundTime
    # reset the clock
    clock = 0

    # reset isTerminated for all the processes
    for i in range(len(processList)):
        processList[i].isTerminated = False

    # Sort the list by the arrival time
    # if the arrival time is the same, sort by the sub priority!
    processList.sort(key=lambda obj: (obj.ArrivalTime, obj.SubPriority))

    # set index variable and list:
    index = 0

    # set the clock to the first process arrival time
    clock = processList[0].ArrivalTime

    while not all(processList[i].isTerminated for i in range(len(processList))):

        # reset the index list
        indexList = []

        for num in range(len(processList)):
            # check the index of the max arrival time the is less or equal to the clock
            if processList[num].ArrivalTime <= clock:
                indexList.append(num)
        # if no process arrived yet, increment the clock and try again
        if all(processList[i].isTerminated for i in indexList):
            clock += 1

        # if there is a process that arrived
        else:
            # run the processes that arrived using the index list indexes in RR fashion
            for j in range(len(indexList)):
                # check if the process has already terminated:
                if not processList[indexList[j]].isTerminated:
                    # check if the remaining time is less than the quantum
                    if processList[indexList[j]].RemainingTime <= quantum:
                        # increment the clock by the remaining time
                        clock += processList[indexList[j]].RemainingTime
                        # set the remaining time to 0
                        processList[indexList[j]].RemainingTime = 0
                        # set the process to terminated
                        processList[indexList[j]].isTerminated = True
                        # calculate the turn around time
                        processList[indexList[j]].TurnAroundTime = clock - processList[indexList[j]].ArrivalTime
                    else:
                        # increment the clock by the quantum
                        clock += quantum
                        # decrement the remaining time by the quantum
                        processList[indexList[j]].RemainingTime -= quantum

            # check if new processes have arrived
            for i in range(len(indexList), len(processList)):
                if processList[i].ArrivalTime <= clock:
                    indexList.append(i)
                    # if there are new processes that arrived, run them in RR fashion
                    # check if the remaining time is less than the quantum
                    if processList[indexList[i]].RemainingTime <= quantum:
                        # increment the clock by the remaining time
                        clock += processList[indexList[i]].RemainingTime
                        # set the remaining time to 0
                        processList[indexList[i]].RemainingTime = 0
                        # set the process to terminated
                        processList[indexList[i]].isTerminated = True
                        # calculate the turn around time
                        processList[indexList[i]].TurnAroundTime = \
                            clock - processList[indexList[i]].ArrivalTime
                    else:
                        # increment the clock by the quantum
                        clock += quantum
                        # decrement the remaining time by the quantum
                        processList[indexList[i]].RemainingTime -= quantum

    # calculate the mean turn around time of RR:
    meanTurnAroundTime.append(np.mean([processList[i].TurnAroundTime for i in range(len(processList))]))

    # reset the RemainingTime for all the processes
    for i in range(len(processList)):
        processList[i].resetRemainingTime()


# ----------------------------------------------------------------------


def main():
    """
    This function is the main function of the code
    :return: None
    """
    try:
        # Get the path of the input file from the command line
        path = sys.argv[1]
    except IndexError:
        print("No input file was given!")
        print("Please enter the path of the input file as a command line argument!")
        exit(-1)

    numOfProcess, data = readDataFromFile(path)
    # print(data)

    # Create a list of objects for each process
    processList = createObjectsForProcesses(data, numOfProcess)
    # for i in range(len(processList)):
    #     processList[i].printProcess()

    # first algorithm - FCFS
    FCFS(processList)
    print("FCFS: mean turnaround = ", round(meanTurnAroundTime[0], 2))
    # second algorithm - LCFS - non-preemptive
    LCFS_NP(processList)
    print("LCFS (NP): mean turnaround = ", round(meanTurnAroundTime[1], 2))
    # third algorithm - LCFS - preemptive
    LCFS_P(processList)
    print("LCFS (P): mean turnaround = ", round(meanTurnAroundTime[2], 2))
    # fourth algorithm - RR - preemptive
    RR(processList, 2)
    print("RR: mean turnaround = ", round(meanTurnAroundTime[3], 2))
    # fifth algorithm - SJF - preemptive
    SJF_P(processList)
    print("SJF: mean turnaround = ", round(meanTurnAroundTime[4], 2))


main()
# This is a class for managing the process

class ProcessParameters:

    def __init__(self, processData, index):
        # Define the attributes of the class:
        self.ArrivalTime = processData[0]
        self.ComputationTime = processData[1]
        self.TurnAroundTime = 0
        self.RemainingTime = self.ComputationTime
        self.isTerminated = False
        self.SubPriority = index

    def printProcess(self):
        print("Arrival Time: ", self.ArrivalTime, ", Computation Time: ,", self.ComputationTime,
              ", Turn Around Time: ", self.TurnAroundTime, ", Remaining Time: ", self.RemainingTime,
              ", isTerminated: ", self.isTerminated, ", Sub Priority: ", self.SubPriority)

    def resetRemainingTime(self):
        self.RemainingTime = self.ComputationTime

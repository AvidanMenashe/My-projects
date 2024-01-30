#this code creates a  xor net to predict the vaules of a xor between two bits.
#if the result is 1 the code should present a value that is close to one.
#if the result is 0 the code should present a vaule that is close to minus one.
#create by avidan meanshe.


import numpy as np
import matplotlib.pyplot as plt

#set of parameters , in the begining they are random from normal distribution
W = np.random.normal(0, 1,(2, 1))
b2 = np.random.normal(0, 1, 1)
b1 = np.random.normal(0, 1, (2, 1))
U = np.random.normal(0, 1, (2, 2))

#this function create an X matrix that will contain the possible value of x (0,0) (0,1) , (1,0) , (1,1)
#the matrix will be 4x2
#create a Y vector that will contain the  right result of each vaule of x
def create_dataset():
    X = np.zeros((4,2))
    Y = np.zeros((4,1))
    X[0,0]=0
    X[0,1]=0
    Y[0]=-1
    X[1,0]=0
    X[1,1]=1
    Y[1]=1
    X[2,0]=1
    X[2,1]=0
    Y[2]=1
    X[3,0]=1
    X[3,1]=1
    Y[3]=-1
    #return the X and Y
    return X,Y


#this function will result the max between Utranspose*x+b1, 0
def h(x):
    global U
    result = np.zeros((2,1))
    temp=(U.T@x).reshape(2,1)+ b1
    result[0]=np.maximum(temp[0],0)
    result[1]=np.maximum(temp[1],0)
    return result

#this function will return the result of wtranspose*h+b2
def f(x):
    global W
    return W.T@h(x)+b2

#this function will return the result of sign(f(x))
#the valuse need to be 0 or 1
def modifeid_f(x):
    return np.where(f(x) >= 0, 1, 0)

def Forward(X):
    Y_hat = np.zeros((4,1))
    for i in range(4):
        Y_hat[i]=f(X[i])
    return Y_hat

#this function will calculate the loss of the model
#Y_hat is vector of the result of f(x) for each x in X
#Y is the vector of the right result of each x in X
def loss(Y_hat,Y):
    return np.sum((Y_hat-Y)**2)


#this function will calculate derevative of the loss function by W
def dW(Y_hat,Y,X):
    global W, U, b1, b2
    sum_derveative =np.zeros((2,1))
    for i in range (4):
        sum_derveative+=-2*(Y[i]-Y_hat[i])*h(X[i].reshape(2, 1))

    return sum_derveative

#this function will calculate the derevative of the loss function by b2
def db2(Y_hat,Y):
    return np.sum(-2*(Y-Y_hat))

#this function will calculate the derevative of the loss function by b1
def db1(Y_hat,Y,X):
    global W,U,b1,b2
    sum_derveative =np.zeros((2,1))
    for i in range (4):
        d_h=np.where(U.T@X[i].reshape(2, 1)+ b1 >= 0, 1, 0)
        sum_derveative+=-2*(Y[i]-Y_hat[i])*d_h*W
    return sum_derveative

#this function will calculate the derevative of the loss function by U
def dU(Y_hat,Y,X):
    global W,U,b1,b2
    sum_derveative =np.zeros((2,2))
    for i in range (4):
        d_h=np.where(U.T@X[i].reshape(2, 1)+ b1 >= 0, 1, 0)
        sum_derveative+=-2*(Y[i]-Y_hat[i])*W@X[i].reshape(1, 2)*d_h
    return sum_derveative

#this function will calculate the gradient descent of the loss function for each parameter
def gradient_descent(Y_hat,Y,X):
    global W,U,b1,b2
    #set the learning rate
    learning_rate=0.001
    #create a list that will store the values of each parameter
    values=[W, U, b1, b2]
    #create a list that will store the gradient of each parameter
    grad=[dW(Y_hat,Y,X),dU(Y_hat,Y,X),db1(Y_hat,Y,X),db2(Y_hat,Y)]
    #go over the parameters and update them by the gradient of each parameter
    for i,param in  enumerate(values):
        param-=learning_rate*grad[i]

def Train(X,Y):
    global W,U,b1,b2
    #set the number of epochs
    epochs=7000
    #create a list that will store the loss of each epoch
    loss_list=[]
    #go over the epochs
    for i in range(epochs):
        #calculate the result of f(x)
        Y_hat = Forward(X)
        #calculate the loss of the model
        loss_list.append(loss(Y_hat,Y))
        #calculate the gradient descent
        gradient_descent(Y_hat,Y,X)
    #return the loss list
    return loss_list

def main():
    global U, W, b1, b2
    #create the dataset , x_mat is the matrix of the possible values of x and Y is the vector of the right result of each x
    #the matrix will be 4x2 and the vector will be 4x1
    X_mat, Y= create_dataset()
    #call the train function until the loss is less than 0.05
    while True:
        # in different run the parameters will be different
        W = np.random.normal(0, 1, (2, 1))
        b2 = np.random.normal(0, 1, 1)
        b1 = np.random.normal(0, 1, (2, 1))
        U = np.random.normal(0, 1, (2, 2))
        #call the train function and get the loss list
        loss_list=Train(X_mat,Y)
        if loss_list[-1]< 0.05 :
            #plot the loss as a function of the epochs
            plt.plot(loss_list)
            plt.xlabel('Epochs')
            plt.ylabel('Loss')
            plt.title('Loss as a function of the epochs')
            plt.grid()
            plt.show()
            #do another forward to the X_mat
            Y_hat = Forward(X_mat)
            #print the result of f(x)
            print(f'the result of the predicted y is\n {(Y_hat)}')
            #print the weights of the model
            print(f'the result of w is\n {(W)}')
            print(f'the result of b1 is\n {(b1)}')
            print(f'the result of b2 is\n {(b2)}')
            print(f'the result of U is\n {(U)}')
            #stop the loop
            break
        else:
            #to make sure that the loss is changing
            print(loss_list[-1])







main()
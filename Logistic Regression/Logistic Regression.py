# Description: This program will train a binary logistic regression model to classify the digits 1 and 2
# The program will show the likelihood as a function of the epoch and the success rate of the model
# The program will use the mnist dataset
# digits 1 and 2 will be classified as 0 and 1 respectively
#created by: Avidan Menashe

import scipy.io as sio
import matplotlib.pyplot as plt
import numpy as np
from sklearn.model_selection import train_test_split
import tqdm



#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~global variables~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
#Our data is mnist for digit 0 until 9 each digit have train and test samples ,the data will be a global variable
#the variable data is a dictionary
data = sio.loadmat('mnist_all.mat')
#set the seed for the random function
seed = 42
#want to get the same random weights each time
np.random.seed(seed)
#get random weights with mean 0 and standard deviation 1
weights = np.random.normal(0, 1, 785)
#reshape the weights to 1x785
weights = weights.reshape(1,785)
#set the learning rate
learning_rate = 0.001
#set the number of the epochs
epochs = 125



def get_data_from_file(j):
    """
    this function will get a number and return the train and test data of the digit j
    :param j: int
    :return: train_data, test_data of the digit j
    """
    global data
    #get the train and test data of the digit j
    train_data = data['train'+str(j)]
    test_data = data['test'+str(j)]
    return train_data, test_data


def view_image(data ,label):
    """
    this function will get the data and the correct label and show random image of the data
    :param data: matrix of the data
    :param label: int that represent the digit of the data
    """
    #randomly choose a number between 0 to the number of samples
    random_number = np.random.randint(0,data.shape[0])
    #get the image from the data
    image = data[random_number]
    #reshape the image to 28x28
    image = image.reshape(28,28)
    #show the image
    plt.title('The label is: '+str(label))
    plt.imshow(image)
    plt.show()

def add_bias(data):
    """
    this function will add to the data, the bias will be a column of ones in the end of each sample
    :param data: matrix of the data
    :return: the new data matrix with the bias
    """
    #add a column of ones to the data
    data = np.insert(data, data.shape[1], 1, axis=1)
    return data

def sigmoid(z):
    """
    this function will calculate the sigmoid of the input
    :param z: input to the sigmoid
    :return: sigmoid result
    """
    return 1/(1+np.exp(-z))

def Y_hat(sample):
    """
    this function will calculate the prediction label of the data by the sigmoid function
    if the result is bigger than 0.5 the label will be 1 else the label will be 0
    :param sample: input (weights*data)
    :return: the prediction label
    """
    #calculate the prediction label
    if sigmoid(sample) > 0.5:
        return 1
    else:
        return 0

def calc_likelihood(sig_result, label):
    """
    this function will calculate the likelihood of the current sample
    :param sig_result: weights*sample
    :param label: the correct label of the sample
    :return: likelihood of the current sample
    """
    #use a small number to avoid log(0) and be more stable numerically
    epsilon = 1e-15
    sig_result = np.clip(sig_result, epsilon, 1 - epsilon)
    return label*np.log(sig_result)+(1-label)*np.log(1-sig_result)


def calc_dw(label,sample,sig_result):
    """
    this function will calculate the derivative of the likelihood by the weights
    :param label: the correct label of the sample
    :param sample: input(data)
    :param sig_result: weights*sample
    :return: the derivative of the likelihood by the weights
    """
    # use a small number to avoid log(0)
    epsilon = 1e-15
    sig_result = np.clip(sig_result, epsilon, 1 - epsilon)
    return (label-sig_result)*sample

def calc_gradient_ascent(dw):
    """
    this function will calculate the new weights by the gradient ascent algorithm
    :param dw: the derivative of the likelihood by the weights
    :return: new weights
    """
    global weights, learning_rate
    return weights + learning_rate*dw


def train(X_train, y_train):
    """
    this function will train the binary logistic regression model
    :param X_train: the train data
    :param y_train: the labels of the train data
    :return: list of the likelihood ,each element in the list is the likelihood of the corresponding epoch
    """
    global weights, learning_rate, epochs
    #reshape y_train to be a column vector
    y_train = y_train.reshape(y_train.shape[0],1)
    # create a list that will store the likelihood of each epoch
    likelihood_list = np.zeros(epochs)
    # craete a variable that will store the dw
    dw = np.zeros(X_train.shape[1])
    #go over the epochs
    for i in tqdm.tqdm(range(epochs)):

        #go over the samples
        for j in range(X_train.shape[0]):
            #calculate the sigmoid result of x*w
            sigmoid_result = sigmoid(np.dot(weights, X_train[j]))
            #calculate the liklihood of the current sample and add it to the likelihood list
            likelihood_list[i]+=calc_likelihood(sigmoid_result, y_train[j]).item()*(1/X_train.shape[0])
            #calculate the dw
            dw += calc_dw(y_train[j],X_train[j],sigmoid_result)*(1/X_train.shape[0])


        #update the weights
        weights = calc_gradient_ascent(dw)

    return likelihood_list

def test(X_test, y_test):
    """
    this function will test the model and return the success rate of the model
    :param X_test: the test data
    :param y_test: the labels of the test data
    :return: success rate of the model
    """
    global weights
    #reshape y_train to be a column vector
    y_test = y_test.reshape(y_test.shape[0],1)
    #create a variable that will store the number of correct predictions
    correct_predictions = 0
    #go over the samples
    for i in range(X_test.shape[0]):
        #calculate the sigmoid result of x*w
        sigmoid_result =np.dot(weights, X_test[i])
        #calculate the prediction label
        prediction = Y_hat(sigmoid_result)
        #check if the prediction is correct
        if prediction == y_test[i]:
            correct_predictions+=1
    #calculate the success rate
    success_rate = (correct_predictions/X_test.shape[0])*100
    return success_rate



#~~~~~~~~~~~~~~~~~~~~~~~~~~~~main~~~~~~~~~~~~~~~~~~~~~~~
if __name__ == '__main__':
    def main():
        #save the data of the train and test of the digit 1 and 2 in variables
        train1, test1=get_data_from_file(1)
        train2, test2=get_data_from_file(2)
        #concatenate the data of the digit 1 and 2
        data=np.concatenate((train1,test1,train2,test2),axis=0)
        #create the labels of the data
        labels = np.concatenate((np.zeros(train1.shape[0]+test1.shape[0]),np.ones(train2.shape[0]+test2.shape[0])),axis=0)
        #add bias to the data
        data = add_bias(data)
        # normalize the data
        data = data / 255
        #split the data to train and test
        X_train, X_test, y_train, y_test = train_test_split(data, labels, test_size=0.25, random_state=seed)
        #train the model and show that the likelihood is increasing
        likelihood_list=train(X_train, y_train)
        plt.plot(likelihood_list)
        plt.xlabel('Epoch')
        plt.ylabel('Likelihood')
        plt.title('Likelihood as a function of the epoch')
        plt.grid()
        plt.show()
        #test the model get the success rate and print it
        success_rate = test(X_test, y_test)
        print('The success rate is: ', success_rate)

    main()
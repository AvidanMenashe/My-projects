#this file will contain the main function that will run the a logistic regression classifier and
#a Neural Network (NN) with one hidden layer
#create by : Avidan Menashe

import numpy as np
import matplotlib.pyplot as plt
from Logistic_Regression_Class import LogisticRegression
import pandas as pd
from Fashion_Net import FashionNet 
import pyarrow

#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~global variables~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
#set the number of classes
num_of_classes = 10

def get_data_from_file(name):
    """
    this function will get the data from the file
    :param name: name of the file
    :return: the data
    """
    #read the data from the file
    data = pd.read_csv(name)
    #get the data as a numpy array
    data = data.values
    #return the data
    return data

def visualize_data(data):
    """
    this function will visualize the data
    for each class we will show random 4 images
    :param data: the data of the images
    """
    global num_of_classes
    #create a dictionary that will store list of each class that contain the index of the samples
    indexses_dict={class_num:[] for class_num in range(num_of_classes)}

    #go over all the samples and add the index to the list of the class
    for i in range(num_of_classes):
        #get all the indexes of the samples that belong to the class i
        indexes_list=np.where(data[:,0]==i)[0]
        #update the dictionary to take 4 random indexes for each class
        indexses_dict[i]=np.random.choice(indexes_list,4,replace=False)

    #create a list that will store the labels
    class_name=[ "T-shirt/top", "Trouser", "Pullover", "Dress", "Coat",
                 "Sandal", "Shirt", "Sneaker", "Bag", "Ankle boot"]
    #create a figure that will store the images
    fig, ax = plt.subplots(10, 4, figsize=(10, 10))
    #go over all the classes
    for i in range(num_of_classes):
        #go over all the samples of the class i
        for j in range(4):
            #show the image
            ax[i,j].imshow(data[indexses_dict[i][j],1:].reshape(28,28), cmap='gray')
            #remove the axis
            ax[i,j].axis('off')
        # Add the class name at the beginning of each row on the left side
        ax[i, 0].text(-15, 15, class_name[i], va='center', ha='right')


    #show the images
    plt.show()



def add_bias(data):
    """
    this function will add a bias to the data,
     the bias will be a column of ones in the end of each sanmple
    :param data: the data without the bias (56,000X784)
    :return: the data with the bias (56,000X785)
    """
    #add a column of ones to the data
    data = np.insert(data, data.shape[1], 1, axis=1)
    return data

def one_hot_encoding(y):
    """
    this function will convert the labels to one hot encoding
    :param y:
    :return:
    """
    #create a matrix of zeros
    one_hot_matrix = np.zeros((y.shape[0], num_of_classes))
    #go over the samples
    for i in range(y.shape[0]):
        #update the one hot matrix
        one_hot_matrix[i, int(y[i])] = 1

    return one_hot_matrix

def split_train_validation(X,y ,train_size=0.8):
    """
    this function will split the data to train and validation
    :param X: the data
    :param y: the correct labels in one hot encoding
    :param train_size: how much of the data will be the train
    :return: train and validation with the correct labels
    """
    #calculate the number of samples that will be in the train
    train_samples = int(X.shape[0] * train_size)
    #get random indexes
    indexes = np.random.choice(X.shape[0], train_samples, replace=False)

    #split the data to train and validation
    X_train = X[indexes]
    y_train = y[indexes]
    X_validation = np.delete(X, indexes, axis=0)
    y_validation = np.delete(y, indexes, axis=0)

    return X_train, y_train, X_validation, y_validation


def show_plots(lost_train,lost_validation,accuracy_train,accuracy_validation):
    """
    this function will show the plots of the lost and the accuracy
    :param lost_train: the lost of the train
    :param lost_validation: the lost of the validation
    :param accuracy_train: the accuracy of the train
    :param accuracy_validation: the accuracy of the validation
    """
    #create a figure that will store the plots
    fig, ax = plt.subplots(2, 1, figsize=(15, 15))
    #show the lost plot
    ax[0].plot(lost_train, label='train', linewidth=2,marker='o')
    ax[0].plot(lost_validation, label='validation', linewidth=2,marker='v')
    #give a title to the axis
    ax[0].set_xlabel('epochs',fontsize=15)
    ax[0].set_ylabel('lost',fontsize=15)
    #give a title to the plot
    ax[0].set_title('lost as a function of the epochs',fontsize=17)
    ax[0].legend(fontsize=12)
    #add grid
    ax[0].grid(True)
    #show the accuracy plot
    ax[1].plot(accuracy_train, label='train',linewidth=2,marker='o')
    ax[1].plot(accuracy_validation, label='validation',linewidth=2,marker='v')
    #give a title to the axis
    ax[1].set_xlabel('epochs',fontsize=15)
    ax[1].set_ylabel('accuracy',fontsize=15)
    #give a title to the plot
    ax[1].set_title('accuracy as a function of the epochs',fontsize=17)
    ax[1].legend(fontsize=12)
    #add grid
    ax[1].grid(True)
    #adjust space between the plots
    plt.subplots_adjust(hspace=0.4)
    #show the plots
    plt.show()





#~~~~~~~~~~~~~~~~~~~~~~~~~~~~main~~~~~~~~~~~~~~~~~~~~~~~
if __name__ == '__main__':
    def main():
        #get the train and test from the file
        train = get_data_from_file('train.csv')
        test = get_data_from_file('test.csv')
        #visualize the data
        visualize_data(train)
        #split the data of the train to X_train and y_train
        X_train = train[:,1:]
        y_train = train[:,0]
        #one hot encoding the labels
        y_train = one_hot_encoding(y_train)

        #normalize the data
        X_train = X_train / 255
        #add bias to the data
        X_train = add_bias(X_train)
        test= add_bias(test)

        #split the data to train and validation
        X_train, y_train, X_validation, y_validation = split_train_validation(X_train, y_train)

        model_logistic=LogisticRegression(X_train.shape,num_of_classes)
        #train the model
        lost_train_logistic,lost_validation_logistic,accuracy_train_logistic,accuracy_validation_logistic=model_logistic.train(X_train,y_train,X_validation,y_validation)

        # show the plots
        show_plots(lost_train_logistic,lost_validation_logistic,accuracy_train_logistic,accuracy_validation_logistic)


        #load the best weights from the file
        best_weights_logistic = np.load('best_weights.npy')
        #set the best weights to the model
        model_logistic.load_weights(best_weights_logistic)


        #forward pass on the test set to get the predictions
        pred_test_logistic=model_logistic.forward(test)
        #get the index of the max value for each sample
        pred_test=np.argmax(pred_test_logistic,axis=1)
        np.savetxt('lr_pred.csv',pred_test,delimiter=',',fmt='%d')

       #~~~~~~~~~~~~~~~~~~~~~~~~~~~~fashion net~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        model_fashionNet = FashionNet(X_train.shape, num_of_classes,150,dropout_rate=0.05)
        #do forward pass
        pred_fashion = model_fashionNet.forward(X_train)
        #train the model
        lost_train_fashion, lost_validation_fashion, accuracy_train_fashion, accuracy_validation_fashion = model_fashionNet.train(X_train, y_train, X_validation, y_validation)
        #show the plots
        show_plots(lost_train_fashion, lost_validation_fashion, accuracy_train_fashion, accuracy_validation_fashion)

        #load the best weights from the files
        best_weights_w1 = np.load('best_weights_w1.npy')
        best_weights_w2 = np.load('best_weights_w2.npy')
        #set the best weights to the model
        model_fashionNet.load_weights(best_weights_w1,best_weights_w2)

        #forward pass on the test set to get the predictions
        pred_test_fashion = model_fashionNet.forward(test)
        #get the index of the max value for each sample
        pred_test_fashion = np.argmax(pred_test_fashion, axis=1)
        #save the predictions to a file
        np.savetxt('NN_pred.csv', pred_test_fashion, delimiter=',', fmt='%d')





    main()
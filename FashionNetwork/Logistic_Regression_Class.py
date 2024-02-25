#this file contains the class of the logistic regression model
import sys
import numpy as np

class LogisticRegression():
    def __init__(self,X_shape,number_of_classes):
        """
        this function will initialize the weights and the bias
        :param X_shape: input shape
        :param number_of_classes: the number of classes
        """

        super().__init__()
        #initialize the weights, the bias is included in the weights
        self.weights =np.random.normal(0, 1, (X_shape[1],number_of_classes))

        #define the value of lambda of L2 regularization
        self.l2_lambda = 0.001

        #define the learning rate
        self.learning_rate = 0.03

        #define the size of the mini batches
        self.batch_size = 128

        #define the number of iterations
        self.num_of_epochs = 200

    def load_weights(self,weights):
        """
        this function will load the weights
        :param weights: the weights
        """
        self.weights = weights
    def forward(self,X):
        """
        this function will calculate the softmax of the input
        :param X: input to the model (data)
        :return: softmax of the input
        """
        return self.softmax(X@self.weights)

    def __call__(self,X):
        """
        this function will return softmax of the input
        :return: the self.forward
        """
        return self.forward(X)

    def calc_lost(self,y,y_hat):
        """
        this function will calculate the lost of the model
        :param y: the correct labels in one hot encoding
        :param y_hat: the predicted probability of the lables
        :return: the lost
        """
        #for numerical stability
        epsilon = 1e-11
        number_of_samples = y.shape[0]
        loss_without_l2 = -(1 / number_of_samples) * np.sum(y * np.log(y_hat + epsilon))
        #add the L2 regularization
        loss= loss_without_l2 + self.l2_lambda * np.sum(self.weights ** 2) / 2


        return loss

    def calc_gradient(self,X,y,y_hat):
        """
        this function will calculate the gradient of the lost
        :param X: input (data)
        :param y: correct labels in one hot encoding
        :param y_hat: predicted probability of the labels
        :return: the gradient of the lost
        """
        number_of_samples = y.shape[0]
        #calculate the gradient without the L2 regularization
        gradient_without_l2 = -(1 / number_of_samples) * X.T @ (y-y_hat)
        #add the L2 regularization
        gradient = gradient_without_l2 + self.l2_lambda * self.weights
        return gradient

    def backward(self,X,y,y_hat):
        """
        this function will update the weights of the model
        by using the gradient descent
        :param X: input (data)
        :param y: correct labels in one hot encoding
        :param y_hat: predicted probability of the labels
        """
        current_gradient = self.calc_gradient(X,y,y_hat)
        #update the weights
        self.weights -= self.learning_rate * current_gradient

    def create_mini_batches(self,X,y,drop=True):
        """
        this function will create mini batches, each mini batch will be a tuple of the data and the labels
        :param X: input (data)
        :param y: correct labels in one hot encoding
        :param batch_size: the size of each batch
        :return: mini batches
        """
        #put the data and the labels together
        data = np.hstack((X, y))
        #shuffle the data by the rows
        np.random.shuffle(data)
        #create the mini batches, each mini batch will be a tuple of the data and the labels
        mini_batches = [(data[i:i + self.batch_size, :-y.shape[1]], data[i:i + self.batch_size, -y.shape[1]:]) for i in
                        range(0, data.shape[0], self.batch_size)]

        #if the last mini batch is not full
        if drop:
            if len(mini_batches[-1][0]) < self.batch_size:
                #drop the last mini batch
                mini_batches = mini_batches[:-1]
        return mini_batches

    def train(self,X_train,y_train,X_validation,y_validation):
        """
        this function will train the model
        :param X_train: the train data
        :param y_train: the correct labels in one hot encoding
        :param X_validation: the validation data
        :param y_validation: the correct labels in one hot encoding
        :return: the lost and accuarcy of the model during the validation and train set
        """
        #~~~~~~~~~~~~~~~~~~~~~~~~train~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        #create mini batches
        mini_batches = self.create_mini_batches(X_train,y_train)
        #save the lost of the model during the validation and train set
        # also save the accuracy
        lost_train = []
        lost_validation = []
        accuracy_train = []
        accuracy_validation = []
        best_accuracy = 0
        best_weights = self.weights

        #go over the epochs
        for epoch in range(self.num_of_epochs):
            mini_batch_loss = []
            mini_batch_accuracy = []
            #go over the mini batches
            for mini_batch in mini_batches:
                #get the data and the labels
                X_mini_batch, y_mini_batch = mini_batch
                #first do a forward pass to get the predictions
                y_hat = self.forward(X_mini_batch)
                #calculate the lost of the mini batch
                lost = self.calc_lost(y_mini_batch,y_hat)
                #update the weights by the backward pass
                self.backward(X_mini_batch,y_mini_batch,y_hat)



                #calculate the accuracy of the mini batch
                mini_batch_accuracy.append(np.mean(np.argmax(y_mini_batch, axis=1) == np.argmax(y_hat, axis=1),dtype=float))
                #check if the current mini batch is better than the best accuracy
                if mini_batch_accuracy[-1] > best_accuracy:
                    best_accuracy = mini_batch_accuracy[-1]
                    #save the best weights
                    best_weights = self.weights

                #add the lost of the mini batch to the list
                mini_batch_loss.append(lost)

            #calculate the lost and the accuracy of the current epoch
            lost_train.append(np.mean(mini_batch_loss))
            accuracy_train.append(np.mean(mini_batch_accuracy))

            #~~~~~~~~~~~~~~~~~~~~~~~~validation~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            #first do a forward pass to get the predictions
            y_hat_val = self.forward(X_validation)
            #calculate the lost of the validation set
            lost_val = self.calc_lost(y_validation,y_hat_val)
            #calculate the accuracy of the validation set
            accuracy_val= np.mean(np.argmax(y_validation, axis=1) == np.argmax(y_hat_val, axis=1),dtype=float)

            #save the validation lost and accuracy of the validation set
            lost_validation.append(lost_val)
            accuracy_validation.append(accuracy_val)

                #print the results of the current epoch
            sys.stdout.write('\r' + f'Epoch: {epoch + 1}')
            sys.stdout.write(f'Train Loss: {lost_train[-1]:.3f} | '
                             f'Train Accuracy: {100 * accuracy_train[-1]:.3f}% ')
            sys.stdout.write(f'Validation Loss: {lost_validation[-1]:.3f} | '
                             f'Validation Accuracy: {100 * accuracy_validation[-1]:.3f}%')
            sys.stdout.flush()

        #save the best weights in  a file
        np.save('best_weights.npy', best_weights)
        #return the lost and the accuracy of the model during the validation and train set
        return lost_train,lost_validation,accuracy_train,accuracy_validation


    @staticmethod
    def softmax(x):
        """
        this function will calculate the softmax of the input
        :param x: input
        :return: softmax of the input
        """
        #add a small number to avoid overflow
        epsilon = 1e-11
        #max normalization
        e_x = np.exp(x - np.max(x, axis=1, keepdims=True))
        return e_x / (np.sum(e_x, axis=1, keepdims=True)+epsilon)
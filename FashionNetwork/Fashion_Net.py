#this file will contain the FashionNet class that is a neural network with one hidden layer
import sys
import numpy as np
class FashionNet():
    def __init__(self,X_shape,number_of_classes,size_of_hidden_layer=100,dropout_rate=0):
        """
        this function will initialize the weights and the bias
        :param X_shape: input shape
        :param number_of_classes: the number of classes
        """

        super().__init__()
        #initialize the weights, the bias is included in the weights
        self.weights1 =np.random.normal(0, 1, (X_shape[1],size_of_hidden_layer))
        self.weights2 = np.random.normal(0, 1, (size_of_hidden_layer+1, number_of_classes))

        #define the value of lambda of L2 regularization for each layer
        self.l2_lambda_layer1 = 0.005
        self.l2_lambda_layer2 = 0.0007

        #define the learning rate
        self.learning_rate = 0.01

        #define the size of the mini batches
        self.batch_size = 64

        #define the number of iterations
        self.num_of_epochs = 350

        #define the rate of the dropout
        self.dropout_rate = dropout_rate

        #define the size of the hidden layer
        self.size_of_hidden_layer = size_of_hidden_layer

    def load_weights(self, weights_w1,weights_w2):
        """
        this function will get the weights and load them to the model
        :param weights: the weights
        """
        self.weights1 = weights_w1
        self.weights2 = weights_w2

    def forward(self,X, training=False):
        """
        this function will calculate the output of the model
        in the first layer we will use ReLU as the activation function
        in the second layer we will use softmax to classify the data
        :param X: input to the model (data)
        :return: the output of the model after move through the layers of the network
        """
        #first layer
        X = X @ self.weights1
        X = self.ReLU(X)
        #apply dropout if it is training
        if training:
            X = self.dropout(X)
        #add column of ones to the data for the bias
        X = np.hstack((X, np.ones((X.shape[0], 1))))
        #second layer
        X = X @ self.weights2
        #Output
        return self.softmax(X)


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
        #add the L2 regularization for each layer
        regularization_layer1 = self.l2_lambda_layer1 * np.sum(self.weights1 ** 2) / 2
        regularization_layer2 = self.l2_lambda_layer2 * np.sum(self.weights2 ** 2) / 2
        loss= loss_without_l2 + regularization_layer1 + regularization_layer2


        return loss

    def calc_gradient(self,X,y,y_hat):
        """
        this function will calculate the gradient of the lost
        :param X: input (data)
        :param y: correct labels in one hot encoding
        :param y_hat: predicted probability of the labels
        :return: the gradient of the lost
        """

        "need to ask chen about the b1,b2"

        number_of_samples = y.shape[0]
        #calculate the gradient for each w in sperate
        #W1
        #gradient_w1_without_l2 = -(1 / number_of_samples) * X.T @ (((y-y_hat) @ self.weights2.T) * self.derivative_ReLU(X @ self.weights1))
        gradient_w1_without_l2 = -(1 / number_of_samples) * X.T @ (((y - y_hat) @ self.weights2[:-1].T) * self.derivative_ReLU(X @ self.weights1))
        #calculate the gradient of the L2 regularization
        gradient_w1_l2= self.l2_lambda_layer1 * self.weights1
        #add the L2 regularization
        gradient_w1 = gradient_w1_without_l2 + gradient_w1_l2

        #W2
        #save the output of the first layer
        output_of_layer1 = self.ReLU(X @ self.weights1)
        #add column of ones to the data for the bias
        output_of_layer1 = np.hstack((output_of_layer1, np.ones((output_of_layer1.shape[0], 1))))
        gradient_w2_without_l2 = -(1 / number_of_samples) * output_of_layer1.T @ (y-y_hat)
        #calculate the gradient of the L2 regularization
        gradient_w2_l2= self.l2_lambda_layer2 * self.weights2
        gradient_w2 = gradient_w2_without_l2 + gradient_w2_l2

        return gradient_w1,gradient_w2


    def backward(self,X,y,y_hat):
        """
        this function will update the weights of the model
        by using the gradient descent
        :param X: input (data)
        :param y: correct labels in one hot encoding
        :param y_hat: predicted probability of the labels
        """
        #calculate the gradient for each w of the model
        current_gradient_w1,current_gradient_w2 = self.calc_gradient(X,y,y_hat)
        #update the weights
        self.weights1 -= self.learning_rate * current_gradient_w1
        self.weights2 -= self.learning_rate * current_gradient_w2


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
        best_weights_w1 = self.weights1
        best_weights_w2 = self.weights2

        #go over the epochs
        for epoch in range(self.num_of_epochs):
            mini_batch_loss = []
            mini_batch_accuracy = []
            #go over the mini batches
            for mini_batch in mini_batches:
                #get the data and the labels
                X_mini_batch, y_mini_batch = mini_batch
                #first do a forward pass to get the predictions
                y_hat = self.forward(X_mini_batch,training=True)
                #calculate the lost of the mini batch
                lost = self.calc_lost(y_mini_batch,y_hat)
                #update the weights by the backward pass
                self.backward(X_mini_batch,y_mini_batch,y_hat)

                #calculate the accuracy of the mini batch
                mini_batch_accuracy.append(np.mean(np.argmax(y_mini_batch, axis=1) == np.argmax(y_hat, axis=1),dtype=float))
                # check if the current mini batch is better than the best accuracy
                if mini_batch_accuracy[-1] > best_accuracy:
                    best_accuracy = mini_batch_accuracy[-1]
                    # save the best weights
                    best_weights_w1 = self.weights1
                    best_weights_w2 = self.weights2


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
            sys.stdout.write(f' Train Loss: {lost_train[-1]:.3f} | '
                             f'Train Accuracy: {100 * accuracy_train[-1]:.3f}% ')
            sys.stdout.write(f'Validation Loss: {lost_validation[-1]:.3f} | '
                             f'Validation Accuracy: {100 * accuracy_validation[-1]:.3f}%')
            sys.stdout.flush()
        # save the best weights in  a file
        np.save('best_weights_w1', best_weights_w1)
        np.save('best_weights_w2', best_weights_w2)
        #return the lost and the accuracy of the model during the validation and train set
        return lost_train,lost_validation,accuracy_train,accuracy_validation

    def dropout(self,X):
        """
        this function will apply the dropout to the input
        :param X: the hidden layer outputs
        :return: the input after the dropout
        """
        #create a mask , the mask will be a matrix of 0 and 1, if the value is less than the dropout rate it will be 0 (dead)
        mask = np.random.rand(self.size_of_hidden_layer) > self.dropout_rate
        #multiply the input by the mask
        X = X * mask

        return X



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

    @staticmethod
    def ReLU(x):
        """
        this function will calculate the ReLU of the input
        :param x: input data
        :return: ReLU of the input
        """
        return np.maximum(0, x)

    @staticmethod
    def derivative_ReLU(x):
        """
        this function will calculate the derivative of the ReLU
        :param x: input data
        :return: the derivative of the ReLU
        """
        #for each element in the input if it is less than 0 the derivative will be 0
        #and if it is bigger than 0 the derivative will be 1
        return np.where(x <= 0, 0, 1)
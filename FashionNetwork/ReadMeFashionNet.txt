This code create a Fashion network.
The data set I worked with was Fashion-MNIST - each example is a 28x28 grayscale
image, associated with a label from the following 10 classes.
To run the Fashion network , you need to run the file "main_Fashion_Net" that includes three main parts:

1. Visualize the Data - Reads the data from train.csv and plot 4 different examples from each class in a grid of 10x4 (i.e., 10 rows, each contains 4 different
examples from the same class). Label each row by the class name.

2. Logistic Regression Classifier- here I implemented a
 multi-class logistic regression classifier that has accuracy rate of 85.48%.

3. Neural Network with One Hidden Layer- here I implemented a NN with one hidden layer and train it on the Fashion-MNIST dataset.
I achieved an accuracy rate of 88.548%.

Restriction:
 In the training part of the Logistic Regression Classifier and of the Neural Network with One Hidden Layer, I saved the best weight of the model I get from all the epochs.
Then, I set the weight of the model to the best weight before I will start the test part, by this, I enable to the model take the best weights from the training part
that it is not necessarily the weight I get from the last epoch in the training part. 
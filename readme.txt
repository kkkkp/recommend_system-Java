=================================
Team
=================================
	Xiaozhou (Patrick) Pu
	Han Zhu

=================================
User Interaction with the System:
=================================
1. The user provides a data set containing users, items and ratings.
2. After data is provided, the user can then ask the system for two things:
	a. Predict a user u's rating on item i.
	b. Return a user u's top n highest rated items.

As a result, the user interface can be simple as long as it supports the above functionalities. The user should not worry or know about the algorithms behind the recommendations, nor should he/she know how and where the data is stored.

=================================
Design
=================================
1. Algorithm Interface:
	M1 & M2:
		The homework introduces kNN as an example of the prediction algorithm and Pearson correlation as a similarity function. The system should be flexible in the sense that it should not only support kNN, but also support other implementations in case the requirements change. That's why we decided Algorithm should be an interface.

		This interface declares three methods:
			a. loadDataCenter():
				The user should be able to upload multiple files, therefore the system needs to support multiple data storages. This method will load the user-desired storage to the algorithm.
			b. getRatingByUserAndItem():
				This method fulfills functionality a.
			c. getTopNRatingItems():
				This method fulfills functionality b.

2. AlgorithmKNN:
	M1 & M2:
		kNN is one possible implementation of the recommendation algorithm. It implements all methods declared in Algorithm interface. It also has the helper methods which are necessary in order to compute the prediction.

3. FileReader:
	M1:
		This class will read a file and break it into a list of lines. Data parsing will be done in DataCenter's loadData() method.
	M2:
		Instead of storing lines of a file, FileReader parses the lines directly without storing them. Therefore it saves a lot of space and data can be loaded faster.

4. DataCenter:
	M1:
		This class represents a data container. One instance of this class corresponds to one file the user uploaded. It relies on FileReader to get the lines of a file. It is responsible for parsing the lines, packaging data in Node and Rating classes, and loading the data into a hashmap.
		DataCenter is also responsible for providing the basic statistics that Algorithm requires.
	M2:
		FileReader is now responsible for parsing the file.

5. Node:
	M1:
		The data points are in the form of User-Item-Rating. The recommendation algorithm requires frequent usage of I_u and U_i. We came up with two potential ways to store the data points:
			a. Have a User class which contains a list of Items, and have an Item class which contains a list of Users. This obviously shows a sign of coupling.
			b. Have a Node class which bundles User and Item together. The hashmap in DataCenter will use Node as key to map to Rating.
		We decided to use option b to avoid coupling between User and Item.
	M2:
		This class is no longer needed because storing it in DataCenter takes too much space.

6. User:
	M1:
		Wrapper class for a user. We will implement an equals() method in order to calculate I_u.
	M2:
		User now contains a hashmap which maps all the movies this user has rated to their ratings.

7. UserContainer:
	A light-weight user wrapper class used in the priority queue in kNN.

8. Movie:
	M1:
		Wrapper class for an movie. We will implement an equals() method in order to calculate U_i.
	M2:
		Movie now contains a hashmap which maps all users who have rated this movie and their ratings.

9. MovieContainer:
	A light-weight movie wrapper class used in the priority queue in kNN.

10. Rating:
	M1:
		Rating can take many forms, such as a double value or boolean values (like/dislike, purchased/not purchased). As it's uncertain which form of rating will the data set use, The Rating class supports all three forms of data.
	M2:
		This class is no longer needed because rating is only in numerical form in the data, so a double will suffice.

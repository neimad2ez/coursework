# coursework
Extension for Coursework for Semester 1
----------------------------------------------------------------------
1) New facility called Sports Hall

I have created a new facility called Sports Hall similar to the other facilities which allows for University to participate in the University Sports league tiered 1-5 with 1 being the most prestigous and 5 being the least presigious. It has the same exact layout as all the other buildings.

I have created the class called "Sports Hall" in the facility package, in the buildings package. Built in noBuildings and newBuildings methods.

2) University Sports League

University Sports League happens during the year where you enter a league depending on level of your Sports Hall, the greater level of Sports Hall the greater the level of competition you're playing against. If there's greater competition there's greater reputation to be gained. For winners and runners up there are cash prizes for the University, so if the University wins something - a medal or a trophy - they are able to gain ECS coins.

University Sports League method is called in line 698. Method is in EcsSim class line 371.

3) Chance for a scandal to occur or a break through in research

To balance out the gain in reputation from the Sports League, I have randomised the chance to be able to get into a scandal which causes a loss of reputation, the severity ranges from: small scandal, medium scandal and a large scandal. Small scandal will make the University lose 100 reputation and the medium and large scandal loss of reputation is randomised. On the other hand, a break through in research could occur which gives the University a randomised chance to gain more reputation.

Method is called event on line 272. Method event is called in line 700.

4) Rewards for having a certain amount of Reputation

When reputation hits a certain threshold, the Universiyt gets invited to attend award ceremonies where they can receive greater recognition and earn a cash prize depending on the award they receive. The awards are: Best upcoming University, becoming a Russell Group University, One of the top universities in the UK, The top University in the UK and the best University in the world.

Method is called reputation and is called on line 704, method reputation is on line 51

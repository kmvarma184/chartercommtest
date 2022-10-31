# chartercommtest

A Reward Management system

It requires an input of User Transaction which basically takes in userId and the amount.
Also when called about the reward statement it can give the reward points on user basis.

Basic 2 apis have been exposed as part of assignment:
GET /reward/user1 to get the reward of a user with id as userId
POST /reward
{
  userId: "user1",
  amount: 34.0
}
to record the transactions.

Basic functionality is implemented with design to extend it to implement the most complex usages.

UserId |  Amount
user1      12.0
user2      20.0
user1      138.0
user2      40.0


user1 = 150 points
user2 = 10 points

Simple spring boot so we can run like a normal jar application on local.


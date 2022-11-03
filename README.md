# chartercommtest

A Reward Management system

It requires an input of User Transaction which basically takes in userId, amount and date.
Also when called about the reward statement it can give the reward points on user basis and on basis of date basis.

Api exposed takes number as last few months but the inner implementation is capable of taking any startDate and endDate and calculate accordingly.

Basic 2 apis have been exposed as part of assignment:
GET /reward/user1 to get the reward of a user with id as userId
POST /reward
{
  userId: "user1",
  payment: {
    amount: 34.0
  }
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



CurL:
curl --location --request GET 'localhost:8080/reward/user2'



curl --location --request POST 'localhost:8080/reward' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userId": "user2",
    "payment": {
        "amount": 50.0
    }
}'


curl --location --request GET 'localhost:8080/reward/user2/last/3'


RewardStatementTest.java has example of dataset being used to calculate reward
as Integeration test.
[Reward Statement.xlsx](https://github.com/kmvarma184/chartercommtest/files/9921779/Reward.Statement.xlsx)

Attached is file with 150 rows and days of various customer data same which is used in the integration test.
Unit test are intentionally not written since being interview assessment was very long itself in the implementation. 
we c<img width="311" alt="Screenshot 2022-11-03 230439" src="https://user-images.githubusercontent.com/117094800/199794004-e763564e-5650-4283-82e2-e39e58a3474f.png">
an discuss further about the extension/ability, please dont consider it to be production ready because it is not. It is an assessment.




##Problem Statement

You are building a Market Place for Self-Employed

You have two actors:
Seller: Posts a project with details requirements. The post also includes the last day and time for accepting bids. 
Buyer (Self-Employed): Bids for work on a fixed price or hourly basis.
 
High Level Requirements:
Assume all projects can be done remotely/online. You do not need to worry about matching by location. The Buyer with the lowest bid automatically wins the bid when the deadline is reached. Lowest bid is displayed on the project page. We have 50K registered Buyers. On average, 100 projects are posted every day. On average, each project receives 50 bids. On the homepage, we need to show 100 most recent projects. You are welcome to assume unspecified requirements to make it better for the customers.

Programming Problem:  
If you are a front-end engineer: Feel free to mock backend service responses and use any library/framework of your choice.
If we are Full Stack Engineer:  Build the service-side part along with the Front End part mentioned. For Building Services you can use In-memory database, also Optionally you are welcome to use a persistent data store of your choice. You are encouraged but not required to take advantage of a service code-generation framework of your choice when performing this exercise. 

###Entities:
    Proejct:
        id
        name
        details
        bidEnd
        creatorId
        winningBidId
        createdOn (not implemented)
        updatedOn (not implemented)
        
    User:
        id
        name
        
    Bid:
        id
        proejctId
        bidderId
        price
        createdOn
        
###Endpoints:
    1. /user - POST
    2. /user/id - GET
    
    3. /projects -> POST
    4. /projects -> GET
    
    5. /project/{id} -> GET
    6. /project/{id} -> PUT (not implemented)
    7. /project/{id} -> DELETE (not implemented)
    
    8. /project/{id}/bids -> POST
    9. /project/{id}/bids -> GET
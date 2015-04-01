# allegro-client
Allegro-client is a client for [allegro-server](https://github.com/awronski/allegro-server).

## Requirements
- Java 8
- [allegro-nice-domain](https://github.com/awronski/allegro-nice-api)

## Install
```
git clone https://github.com/awronski/allegro-client.git
cd allegro-client
mvn package install
```

# How to use it?
Create an instance:
```java
AllegroClient client = new AllegroClient.Builder()
    .endpoint(address)
    .username(user)
    .password(password)
    .logLevel(RestAdapter.LogLevel.NONE)
    .build();
```

## Auctions
Performing operations on auction
```java
IAuctionService auctionService = client.getAuctionService();

List<Auction> allAuctions = auctionService.getAllAuctions();
List<Auction> openAuctions = auctionService.getOpenAuctions(true);
Optional<Auction> auction = auctionService.getAuctionById(itemId);
ChangedQty changedQty = auctionService.changeQty(itemId, 9);
List<FinishAuctionFailure> failuresList = auctionService.finish(Arrays.asList(itemId1, itemId2, itemId3));
```

## Creating new auction
```java
List<NewAuctionField> fields = new AuctionBuilder()
        .category(76661)
        .title("Title")
        .city("Warszawa")
        .zip("01-111")
        .state(State.MAZOWIECKIE)
        .color(Color.BLACK)
        .desc("This is test description")
        .price(1.99)
        .priceForLetter(7.99)
        .priceForNextItemInLetter(0.0)
        .qtyInLetter(50)
        .priceForCourier(14.99)
        .priceForNextItemInCourier(0.0)
        .qtyInCourier(250)
        .build();
CreatedAuction createdAuction = auctionService.create(fields);
```

## Payments
```java
List<Payment> lastPayments = client.getPaymentService().getLastPayments(10);
List<Payment> unprocessed = client.getPaymentService()getUnprocessed();

PaymentProcessed processed = client.getPaymentService().process(transactionId, amount, yourRef);
```

## Search Payments
```java
List<Payment> payments = service.searchPayments(new SearchPayment.Builder()
        .withMsg(false)
        .withInvoice(false)
        .from(fromDate)
        .to(doDate))
        .limit(100)
        .build());
```

## Journal
```java
Date to = Date.from(LocalDateTime.now().minusDays(10).atZone(ZoneId.systemDefault()).toInstant());

SearchJournal searchJournal = new SearchJournal.Builder()
        .changeType(JournalType.CHANGE)
        .to(to)
        .build();

List<Journal> lastJournals = client.getJournalService().search(searchJournal);
```

## Deals
```java
List<Deal> lastDeals = client.getDealService().getLastDeals(10)
```

## Categories' Forms
```java
List<FormField> fields = client.getFormFieldService().getFormFields(76661, true);
```

### _... work in progress_

License
=======

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
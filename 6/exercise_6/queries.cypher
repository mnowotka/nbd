1. MATCH p=shortestPath( (darjeeling:town {name:"Darjeeling"})-[*]-(sandakphu:peak {name:"Sandakphu"}) ) RETURN p

2. MATCH p=shortestPath( (darjeeling:town {name:"Darjeeling"})-[*]-(sandakphu:peak {name:"Sandakphu"}) )
   WHERE ALL (x IN RELATIONSHIPS(p) WHERE x.winter = 'true')
   RETURN p

3. MATCH p=(darjeeling:town {name:"Darjeeling"})-[*]->(sandakphu:peak {name:"Sandakphu"})
   RETURN p AS path,
   reduce(distance=0, r in relationships(p) | distance+r.distance) AS totalDistance
   ORDER BY totalDistance ASC

4. MATCH (airport:Airport)
   RETURN airport.name, size((airport)<-[:ORIGIN]-()) as degree
   ORDER BY degree DESC

5. // match a path
    MATCH p = (origin:Airport {name: 'LAX'})-[:ORIGIN|DESTINATION*..5]-(destination:Airport)
    WHERE

      // we are not interested in round trips as LAX is a trivially achievable destination
      origin <> destination AND

      // more conditions to impose on each considered path Airport -> Flight -> Airport -> Flight -> ... -> Airport
      ALL(i IN RANGE(0, LENGTH(p)-1) WHERE

        // origin of one trip is a destination of another, we want an aternating cycle of relations: ORIGIN -> DESTINATION -> ORIGIN -> DESTINATION
        TYPE(RELATIONSHIPS(p)[i]) = ['ORIGIN', 'DESTINATION'][i%2] AND


        (
          // if we are dealing with Airport node, skip the check, the rest of the condition is only for Flights
          "Airport" IN LABELS(NODES(p)[i]) OR 

          // if this is the last flight in the path, skip this path because we are comparing a previous flight with the next one
          (i + 2) > LENGTH(p) OR

          // check if the end of the previous flight is at least 30 minutes before the beginning of the next flight so we can make a transfer
          (apoc.date.parse(NODES(p)[i].date,'m','MM/dd/yyyy hh:mm:ss') + NODES(p)[i].duration + 30) < apoc.date.parse(NODES(p)[i+2].date,'m','MM/dd/yyyy hh:mm:ss'))
      ) AND

      // compute the cost of the trip check if it's less than a threshold
      REDUCE(s = 0, n IN [k IN RANGE(1, LENGTH(p), 2) | NODES(p)[k]] |
        s + [(n)<-[:ASSIGN]-(ticket) | ticket.price][0]
      ) < 3000

    // we are done, return destination
    RETURN destination

6. // match paths between LAX and DAY
    MATCH p = (origin:Airport {name: 'LAX'})-[:ORIGIN|DESTINATION*..5]-(destination:Airport {name: 'DAY'})
    WHERE

      // more conditions to impose on each considered path Airport -> Flight -> Airport -> Flight -> ... -> Airport
      ALL(i IN RANGE(0, LENGTH(p)-1) WHERE

        // origin of one trip is a destination of another, we want an aternating cycle of relations: ORIGIN -> DESTINATION -> ORIGIN -> DESTINATION
        TYPE(RELATIONSHIPS(p)[i]) = ['ORIGIN', 'DESTINATION'][i%2] AND

        (
          // if we are dealing with Airport node, skip the check, the rest of the condition is only for Flights
          "Airport" IN LABELS(NODES(p)[i]) OR

          // if this is the last flight in the path, skip this path because we are comparing a previous flight with the next one
          (i + 2) > LENGTH(p) OR

          // check if the end of the previous flight is at least 30 minutes before the beginning of the next flight so we can make a transfer
          (apoc.date.parse(NODES(p)[i].date,'m','MM/dd/yyyy hh:mm:ss') + NODES(p)[i].duration + 30) < apoc.date.parse(NODES(p)[i+2].date,'m','MM/dd/yyyy hh:mm:ss'))
      )

    // we are done, return destination and the total cost of the trip
    RETURN p, REDUCE(s = 0, n IN [k IN RANGE(1, LENGTH(p), 2) | NODES(p)[k]] | s + [(n)<-[:ASSIGN]-(ticket) | ticket.price][0]) as totalPrice

    // order by cheapest
    ORDER BY totalPrice ASC

7. // match paths between LAX and DAY
   MATCH p = (origin:Airport {name: 'LAX'})-[:ORIGIN|DESTINATION*..5]-(destination:Airport {name: 'DAY'})
    WHERE

     // more conditions to impose on each considered path Airport -> Flight -> Airport -> Flight -> ... -> Airport
      ALL(i IN RANGE(0, LENGTH(p)-1) WHERE

         // origin of one trip is a destination of another, we want an aternating cycle of relations: ORIGIN -> DESTINATION -> ORIGIN -> DESTINATION
         TYPE(RELATIONSHIPS(p)[i]) = ['ORIGIN', 'DESTINATION'][i%2] AND


         (
           // if we are dealing with Airport node, skip the check, the rest of the condition is only for Flights
           "Airport" IN LABELS(NODES(p)[i]) OR

           // if this is the last flight in the path, skip this path because we are comparing a previous flight with the next one
           (i + 2) > LENGTH(p) OR

           // check if the end of the previous flight is at least 30 minutes before the beginning of the next flight so we can make a transfer
           (apoc.date.parse(NODES(p)[i].date,'m','MM/dd/yyyy hh:mm:ss') + NODES(p)[i].duration + 30) < apoc.date.parse(NODES(p)[i+2].date,'m','MM/dd/yyyy hh:mm:ss'))
       )

     // we are done, return destination and the total cost of the trip
     RETURN p, REDUCE(s = 0, n IN [k IN RANGE(1, LENGTH(p), 2) | NODES(p)[k]] | s + [(n)<-[:ASSIGN]-(ticket) | ticket.price][0]) as totalPrice

     // order by cheapest
     ORDER BY totalPrice ASC

     // return the cheapest
     LIMIT 1

8.  // match paths between LAX and DAY
    MATCH p = (origin:Airport {name: 'LAX'})-[:ORIGIN|DESTINATION*..5]-(destination:Airport {name: 'DAY'})
    WHERE

      // we are only interested in business class
      ALL (t IN RELATIONSHIPS(p) WHERE TYPE(t) <> 'ASSIGN' OR t.class = 'business') AND

      // more conditions to impose on each considered path Airport -> Flight -> Airport -> Flight -> ... -> Airport
      ALL(i IN RANGE(0, LENGTH(p)-1) WHERE

        // origin of one trip is a destination of another, we want an aternating cycle of relations: ORIGIN -> DESTINATION -> ORIGIN -> DESTINATION
        TYPE(RELATIONSHIPS(p)[i]) = ['ORIGIN', 'DESTINATION'][i%2] AND
        (
          // if we are dealing with Airport node, skip the check, the rest of the condition is only for Flights
          "Airport" IN LABELS(NODES(p)[i]) OR

          // if this is the last flight in the path, skip this path because we are comparing a previous flight with the next one
          (i + 2) > LENGTH(p) OR

          // check if the end of the previous flight is at least 30 minutes before the beginning of the next flight so we can make a transfer
          (apoc.date.parse(NODES(p)[i].date,'m','MM/dd/yyyy hh:mm:ss') + NODES(p)[i].duration + 30) < apoc.date.parse(NODES(p)[i+2].date,'m','MM/dd/yyyy hh:mm:ss'))
      )

    // we are done, return destination
    RETURN p

9. MATCH (n:Flight)
   WITH collect(n.airline) as airlines
   UNWIND airlines as un
   return un, count(un) as cnt

10. MATCH p=(first:Airport)<-[:ORIGIN]-(f1:Flight)-[:DESTINATION]->(second:Airport)<-[:ORIGIN]-(f2:Flight)-[:DESTINATION]->(third:Airport)
    WHERE first <> second AND second <> third AND first <> third AND
    f1 <> f2 AND
    (apoc.date.parse(f1.date,'m','MM/dd/yyyy hh:mm:ss') + f1.duration + 30) < apoc.date.parse(f2.date,'m','MM/dd/yyyy hh:mm:ss')
    RETURN p, REDUCE(s = 0, n IN [k IN RANGE(1, LENGTH(p), 2) | NODES(p)[k]] | s + [(n)<-[:ASSIGN]-(ticket) | ticket.price][0]) as totalPrice
    ORDER BY totalPrice ASC
    LIMIT 1

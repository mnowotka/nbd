1. MATCH (movies:Movie) RETURN movies
2. MATCH (hugo:Person {name: "Hugo Weaving"})-[:ACTED_IN]->(hugoMovies) RETURN hugoMovies
3. MATCH (hugo:Person {name: "Hugo Weaving"})-[:ACTED_IN]->(hugoMovies)<-[:DIRECTED]-(directors) RETURN directors
4. MATCH (hugo:Person {name:"Hugo Weaving"})-[:ACTED_IN]->(m)<-[:ACTED_IN]-(coActors) RETURN coActors
5. MATCH (movies:Movie)<-[:ACTED_IN]-(people:Person)-[:ACTED_IN]->(:Movie {title: "The Matrix"}) RETURN movies
6. MATCH (person:Person)-[acted:ACTED_IN]-(:Movie) RETURN person.name, count(acted.roles)
7. MATCH (director:Person)-[:DIRECTED]->(movie:Movie)<-[:WROTE]-(writer:Person) WHERE writer = director return director.name, movie.title
8. MATCH (hugo:Person {name: "Hugo Weaving"})-[:ACTED_IN]->(hugoMovies), (keanu:Person {name: "Keanu Reeves"})-[:ACTED_IN]->(keanuMovies) WHERE hugoMovies = keanuMovies return hugoMovies
9.a CREATE (CaptainAmerica:Movie {title:'Captain America', released:2011, tagline:'The first Avenger'})
    CREATE (Joe:Person {name:'Joe Johnston', born:1950})
    CREATE (Christofer:Person {name:'Christofer Markus', born:1970})
    CREATE (Stephen:Person {name:'Stephen McFeely', born:1969})
    CREATE (Chris:Person {name:'Chris Evans', born:1981})
    CREATE (Samuel:Person {name:'Samuel L. Jackson', born:1948})
    CREATE
       (Joe)-[:DIRECTED]->(CaptainAmerica),
       (Christofer)-[:WROTE]->(CaptainAmerica),
       (Stephen)-[:WROTE]->(CaptainAmerica),
       (Chris)-[:ACTED_IN {roles:['Steve Rogers', 'Captain America']}]->(CaptainAmerica),
       (Samuel)-[:ACTED_IN {roles:['Nick Fury']}]->(CaptainAmerica)

9.b MATCH (hugo:Person {name: "Hugo Weaving"})
    MATCH (CaptainAmerica:Movie {title:'Captain America'})
    CREATE (hugo)-[:ACTED_IN {roles: ['Johann Schmidt', 'Red Skull']}]->(CaptainAmerica)

9.c MATCH (people:Person)-[relatedTo]-(CaptainAmerica:Movie {title: "Captain America"}) RETURN people, relatedTo, CaptainAmerica

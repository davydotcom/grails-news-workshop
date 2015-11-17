# Step 4 Instructions

Now that we have implemented a means to create and list new postings, as well as restrict access to creating those postings unless the user is logged in, we are going to revisit how we display posts. We will implement a voting mechanism that allows logged in users to either upvote or downvote an article. We will also add code to prevent a user from making multiple votes per article.

## Scoring Algorithm

The following scoring algorithm is the base  scoring algorithm for Hacker News:

```
Score = (P-1) / (T+2)^G

where,
P = points of an item (and -1 is to negate submitters vote)
T = time since submission (in hours)
G = Gravity, defaults to 1.8 in news.arc
```

## Steps

* Add a DomainClass for keeping track of votes
* Add Controller Actions to apply votes
* Create service to calculate and persist a score on a Post
* Setup Job to periodically refresh the score

## Additional Resources

This lab also adds the quartz dependency to trigger `scoringService.refreshAllScores()` periodically. This can be seen in the `ScoreUpdateJob.groovy` file

```
dependencies {
  compile 'org.grails.plugins:quartz:2.0.0'
}
```
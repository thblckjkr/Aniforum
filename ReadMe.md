# Aniforum for anilist

> Abandoned project. I still think it could work well as a concept, but I don't have the time to make it work.

Query para ver un post de un foro
```graphql
query ($threadId: Int) {
  Thread(id: $threadId) {
    id
    title
    body
    userId
    replyCount
    viewCount
    isLocked
    isSticky
    isSubscribed
    isLiked
    likeCount
    repliedAt
    createdAt
    user {
      id
      name
      avatar {
        large
      }
    }
    categories {
      id
      name
    }
    mediaCategories {
      id
      title {
        userPreferred
      }
      coverImage {
        large
      }
      type
      format
    }
  }
}
```

Query para ver los comentarios de un foro

```graphql
query($threadId:Int,$page:Int){
  Page(page:$page,perPage:15){
    pageInfo{
      total perPage currentPage lastPage hasNextPage
    }
    threadComments(threadId:$threadId){
      id threadId comment isLiked likeCount createdAt
      	user{
          id name donatorTier donatorBadge moderatorStatus
          	avatar{large}
        }
      childComments
    }
  }
}
```

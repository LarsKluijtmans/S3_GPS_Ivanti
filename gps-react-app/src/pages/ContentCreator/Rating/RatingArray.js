export let ratingArray = {
        id: 1,
        name: "windows",
        rating: {
                oneStar: 5,
                twoStar: 7,
                threeStar: 10,
                fourStar: 15,
                fiveStar: 30,
                averageStar: ((5+7*2+10*3+15*4+30*5)/(5+7+10+15+30)).toFixed(1)
        }
    }


<template>
    <div class="container marketing">

      <div class="custom_search_container">
       <form class="fleft" name="topSearchForm" id="topSearchForm" action="/goods/search">
        <input type="hidden" name="keyword_log_flag" value="Y" />
        <div class ="search-input">
        <b-img type="image" @click="deleteE(exid, experience)" style="cursor:pointer" v-bind:src="require(`@/assets/img/icons8-search-50.png`)" width="25px"></b-img>
        <input type="text" name="search_text" value=""  autocomplete="off" autofocus title="검색해보아요~" class="search_top" >
        </div>
        </form>
      </div>
      
      <hr class="featurette-divider">
   
   
   
       <div>
    <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="4000"
      controls
      indicators
      background="#ababab"
      img-width="1024"
      img-height="480"
      style="text-shadow: 1px 1px 2px #333;"
      @sliding-start="onSlideStart"
      @sliding-end="onSlideEnd"
    >
      <!-- Text slides with image -->
      <b-carousel-slide
        caption="First slide"
        text="Nulla vitae elit libero, a pharetra augue mollis interdum."
        img-src="https://picsum.photos/1024/480/?image=52"
      ></b-carousel-slide>

      <!-- Slides with custom text -->
      <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=54">
        <h1>Hello world!</h1>
      </b-carousel-slide>

      <!-- Slides with image only -->
      <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=58"></b-carousel-slide>

      <!-- Slides with img slot -->
      <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
      <b-carousel-slide>
        <template v-slot:img>
          <img
            class="d-block img-fluid w-100"
            width="1024"
            height="480"
            src="https://picsum.photos/1024/480/?image=55"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>

      <!-- Slide with blank fluid image to maintain slide aspect ratio -->
      <b-carousel-slide caption="Blank Image" img-blank img-alt="Blank image">
        <p>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eros felis, tincidunt
          a tincidunt eget, convallis vel est. Ut pellentesque ut lacus vel interdum.
        </p>
      </b-carousel-slide>
    </b-carousel>

    <p class="mt-4">
      Slide #: {{ slide }}<br>
      Sliding: {{ sliding }}
    </p>
  </div>


   
   
   
   
    </div>
    

    

    

</template>

<script>
import constants from "../../lib/constants.js";
import axios from "axios";

//const SERVER_URL = "http://localhost:8080";

export default {
  name: "ManageExperience",
  data: () => {
    return {
        slide: 0,
        sliding: null
    };
  },
  beforeCreate () {
 
   
   }
   ,

   computed: {
    uidState(tag) {
      return this.uid.length > 0 ? true : false;
    },
  
  },
  created() {
    axios
      .get(this.$SERVER_URL + `/temp`, {
       
      })
      .then((response) => {
        // alert(this.$SERVER_URL + `/experience/Tags`);
       console.log(response.data.object);
      })
      .catch((error) => {
      });
  },

  methods:{
    onSlideStart(slide) {
        this.sliding = true
      },
      onSlideEnd(slide) {
        this.sliding = false
      } ,

      test(){
         axios
      .get(this.$SERVER_URL + `/temp`, {
       
      })
      .then((response) => {
        // alert(this.$SERVER_URL + `/experience/Tags`);
       console.log(response.data.object);
       
          

       
      })
      .catch((error) => {
      });
      }
  }
};
</script>

<style>


.search-input input{
      width: 475px;
    font-size: 16px;
    border: 0;
    outline: 0;
    font-family: "Noto Sans KR", "NanumGothic", "Nanum Gothic", "Malgun Gothic", "Apple SD Gothic Neo", dotum, sans-serif;
    font-weight: 500;
    color: #000;
    font-size: 20px;
    
    border-bottom: 3px solid #00000022
}

.search_top {
    height: 45px;
    font-size: 19px;
    padding-top: 5px;
    font-weight: 500;
    color: white;
    background: #eeeeeeff;
    letter-spacing: -0.0px;
}

.custom_search_container{
  margin: auto;
  text-align: center;
}

</style>
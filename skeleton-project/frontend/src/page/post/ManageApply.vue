<template>
<div>

   <!-- <div class="row">
       <modal name="example"
         @before-open="beforeOpen"
         @before-close="beforeClose">
    <span>Hello, {{ periods }}!</span>
    </modal>
   </div> -->
    <div v-if="is_show">
      
       <modal name="example"
         @before-open="beforeOpen"
         @before-close="beforeClose"
         draggable=""
         >
        <div class="custom-modal-header">
            지원회사 기간 선택
            <div class="modal-button">
              <b-img @click="$modal.hide('example')"  style="cursor:pointer" v-bind:src="require(`@/assets/img/icons8-xbox-x-50.png`)" width="30px"></b-img>
            </div>
        </div>
        <div class="modal-contents" >
            <select v-on:input="updateValue($event.target.value)" >
              <option v-for="option in periods">{{ option.apTerm }}</option>
            </select>    
            
            <div>
              <p> 선택하신 기간은 {{search_input}}입니다. </p>
              <p> 맞으시면 확인 버튼을 눌러주세요.</p>
            </div>
              <button @click="searchByPeriod(search_input)" type="button" class="btn btn-primary">확인</button>
         </div>
       </modal>
    
    </div> 

    <div class="container marketing">

      <div class="custom_search_container">
       <form class="fleft" name="topSearchForm" id="topSearchForm" action="/goods/search">
        <input type="hidden" name="keyword_log_flag" value="Y" />
        <div class ="search-input">
        <b-img type="image" @click="deleteE(exid, experience)" style="cursor:pointer" v-bind:src="require(`@/assets/img/icons8-search-50.png`)" width="25px"></b-img>
        <input type="text" v-model="search_input" value=""  autocomplete="off" autofocus title="검색해보아요~" class="search_top" >
        <b-img type="image" @click="calendar()" style="cursor:pointer" v-bind:src="require(`@/assets/img/icons8-calendar-50.png`)" width="25px"></b-img>
  
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
    

    

    
</div>
</template>

<script>
import constants from "../../lib/constants.js";
import axios from "axios";
import Vue from "vue";


//const SERVER_URL = "http://localhost:8080";

export default {
  name: "ManageExperience",
  data: () => {
    return {
        slide: 0,
        sliding: null,
        periods: [],
        is_show:false,
        search_input:""
    };
  },
  mounted(){
        this.$modal.show('example')
     
    },
   computed: {
    uidState(tag) {
      return this.uid.length > 0 ? true : false;
    },
  
  },
  created() {


     //지원기간 가져오기, 없을경우만
    if(constants.APPLY_PERIOD == null){
      axios
        .get(this.$SERVER_URL + `/temp`, {
        
        })
        .then((response) => {
          
        //console.log(response.data.object);
        constants.APPLY_PERIOD = response.data.object;
        this.periods = constants.APPLY_PERIOD;
        console.log(constants.APPLY_PERIOD);
        //
        })
        .catch((error) => {
        });
    }else{
       this.periods = constants.APPLY_PERIOD;
    }

  


  },

  methods:{
    onSlideStart(slide) {
        this.sliding = true
      },
      onSlideEnd(slide) {
        this.sliding = false
      } ,
      calendar(){
        this.is_show = true;
        this.$modal.show('example');
        
      }
      ,
      updateValue: function (period) {
            this.search_input = period;
      },
      searchByPeriod: function(period){
          
          //검색어 없음, 처음 미정일때
          if(period == ""){
            alert("기간을 선택하세요.");
            return;
          }
          

          //검색어 있으면
          this.$modal.hide('example');
          alert(period);
      }
      ,

    beforeOpen (event) {
    this.is_show= true;
    console.log('Opening...')
    },
    beforeClose (event) {
      console.log('Closing...')
       this.is_show= false;
       event.cancel()
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


.custom-modal-header{
  
  display: flex;
    align-items: flex-start;
    justify-content: space-between;
    padding: 1rem 1rem;
    border-bottom: 1px solid #dee2e6;
    border-top-left-radius: calc(0.3rem - 1px);
    border-top-right-radius: calc(0.3rem - 1px);
    
  background: #eeeeee;
  font-weight: 400;

}

.modal-contents{
  text-align: center;
}

.modal-button{
  text-align: right;
}


</style>
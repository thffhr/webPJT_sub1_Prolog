<template>
<div>

  


    <!-- 기간 선택 모달 -->
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
              <p> 선택하신 기간은 {{search_input_period}}입니다. </p>
              <p> 맞으시면 확인 버튼을 눌러주세요.</p>
            </div>
              <button @click="searchByPeriod(search_input_period)" type="button" class="btn btn-primary">확인</button>
         </div>
       </modal>
    
    </div> 
    <!-- 기간 선택 모달 -->
  


    <!-- 검색 창 -->
    <div class="container marketing">

      <div class="custom_search_container">
       <form class="fleft" name="topSearchForm" id="topSearchForm" action="/goods/search">
        <input type="hidden" name="keyword_log_flag" value="Y" />
        <div class ="search-input">
          <b-img type="image" @click="deleteE(exid, experience)" style="cursor:pointer" v-bind:src="require(`@/assets/img/icons8-search-50.png`)" width="25px"></b-img>
          <input type="text" v-model="search_input_text" value=""  autocomplete="off" autofocus title="회사명으로 검색해보아요~" class="search_top_text" >
          <input readonly type="text" v-model="search_input_period" value=""  autocomplete="off" autofocus title="기간선택해보아요~" class="search_top_period" >
          <b-img type="image" @click="calendar()" style="cursor:pointer" v-bind:src="require(`@/assets/img/icons8-calendar-50.png`)" width="25px"></b-img>
        </div>
        </form>
      </div>
      
      <hr class="featurette-divider">
    <!-- 검색 창 -->
   
   
   <!-- 케러셀 -->
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


      <div v-for="(apply,apid) in apply_lists" :key="apply.apid">
             <div class="custom-contents" @click="view_apply_detail(apply)">
                 
            <b-carousel-slide
                caption="First slide"
                text="Nulla vitae elit libero, a pharetra augue mollis interdum."
                img-src="https://picsum.photos/1024/480/?image=52"
                >
                      <!-- 제목 -->
                        <div>
                            <h2>{{apply.apCompany}}</h2>
                        </div>
                        <div>
                            <h4>{{apply.apTerm}}</h4>
                        </div>

                      <!-- 날짜 -->
                        <div class="date-align">
                          <small>{{apply.ap_term}} ~ {{apply.ap_term}}</small>
                        </div>

                      <!-- 내용 -->
                      <div>
                          <p class="txt_line"> {{apply.apDesc}} </p>
                      </div>
                </b-carousel-slide>
                
                </div>
                
      </div>


    </b-carousel>
  </div>
  <!-- 케러셀 -->


  <!-- 임시 지원목록 상세 -->
  
          <!-- 지원목록 -->
        <div v-bind="selected_apply" v-if="isEmptyApply()">
            <div class="custom-temp col-md-12">
              <!-- 제목 -->
                <div>
                    <h2>{{selected_apply.apCompany}}</h2>
                </div>
                <div>
                    <h4>{{selected_apply.apTerm}}</h4>
                </div>

              <!-- 날짜 -->
                <div class="date-align">
                  <small>{{selected_apply.ap_term}} ~ {{selected_apply.ap_term}}</small>
                </div>

              <!-- 내용 -->
              <div>
                  <p class="txt_line"> {{selected_apply.apDesc}} </p>
              </div>
            </div>
        </div><!-- /지원목록 -->

  <!-- 임시 지원목록 리스트 -->
  
  </div>
  <!-- Container 끝-->  

    

    
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
        search_input_period:"",
        search_input_text:"",

        apply_lists:[],
        selected_apply:[]
    };
  }
  ,
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

    //지원목록 가져오기
    axios
      .get(this.$SERVER_URL + `/apply`, {
        params: {
          uid: localStorage["uid"],
        },
      })
      .then((response) => {
        
        console.log(response)
        console.log(response.data.object);
        this.apply_lists = response.data.object;
        console.log(response.data.object[0].portfolioTags);
        
      })
      .catch((error) => {
        console.log(error);   
      });
  


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
            this.search_input_period = period;
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
    },
    view_apply_detail(in_select_apply){
      alert("임시");
      this.selected_apply = in_select_apply;
    },
    isEmptyApply(){
      if(this.selected_apply == ""){
        return false;
      }
      return true;
    }
  }
};
</script>

<style>


.search-input{
    width: 70%;
    text-align: center;
}

.search_top_period {
     width: 20%;
    font-size: 16px;
    border: 0;
    outline: 0;
    font-family: "Noto Sans KR", "NanumGothic", "Nanum Gothic", "Malgun Gothic", "Apple SD Gothic Neo", dotum, sans-serif;
    font-weight: 500;
    color: #000;
    font-size: 20px;
    
    border-bottom: 3px solid #00000022;
    height: 45px;
    background: #eeeeeeff;
}

.search_top_text {
    width: 50%;
    font-size: 16px;
    border: 0;
    outline: 0;
    font-family: "Noto Sans KR", "NanumGothic", "Nanum Gothic", "Malgun Gothic", "Apple SD Gothic Neo", dotum, sans-serif;
    font-weight: 500;
    color: #000;
    font-size: 20px;
    
    border-bottom: 3px solid #00000022;
    height: 45px;
    background: #eeeeeeff;
}

.custom_search_container{
  margin: auto;
  text-align: -webkit-center;
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

.custom-temp{
  background: #eeeeee;
  border-radius: 0.5em;
  border: 1px solid rgba(0, 0, 0, 0.125);
  margin-top: 5%;
}


</style>
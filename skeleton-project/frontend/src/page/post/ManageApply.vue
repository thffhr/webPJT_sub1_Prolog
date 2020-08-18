<template>


<div>




 <div>
  <!-- <main class="flexbox">

      <Board id ="board-1">
        <Card id="card-1" draggable="true">
          <p>card One </p>
        </Card>
      </Board>

      <div>
        <p v-on:test="test2(1)"></p>
      </div>

      <Board id ="board-2">
        <Card id="card-2" draggable="true">
          <p>card Two </p>
        </Card>
      </Board>
  </main> -->
</div> 


  <!-- <div class="about"> 
      <div class="left">
         

        <div ref="right" id="drop-target">
        </div>
      </div> 

        <div class="right"> 
          <div id="display" ref="display1">Display</div> 
        </div> 

  </div> -->

    <!-- 네비게이션 오른쪽 -->
   <div class="temp" >


    <!-- 내용 -->
    <div class="custom-nav-right-contents ">
    <b-collapse id="collapse_navbar3" class="custom-pageMenue-right" >
      
      <div class="custom-left-list">
        
        <h4 style="color:black"> 포트폴리오</h4>
        <div class="custom-left col-lg-12">
             <Board id="board-1">
                      <div  v-for="(port, pid) in nav_port_list" :key="port.pid">
                        <Card :id="'card-p-' + port.pid" draggable="true">
                    
                          <div>{{port.title}}</div>
                        </Card>
                  
                      </div>
                      
              </Board>
          </div>
       

       <hr class="featurette-divider">


       <h4 style="color:black">경험</h4>

          <div class="custom-left  col-lg-12">
           <Board id="board-2">
     
             <div   v-for="(ex, exid) in nav_ex_list" :key="ex.exid">
                  <Card  :id="'card-e-' + ex.exid" draggable="true">
                   <div >{{ex.title}}</div>
                  </Card>
              </div>
              
            
            </Board>
          </div>

      </div>
    </b-collapse>


      <!-- 버튼 -->
     <div class="right_btn">
       <div @click="changeLeftImg()">
        <b-img 
          
          class="custom-apply-Btn" 
          v-b-toggle.collapse_navbar3
          style="cursor:pointer"
          :pressed.sync="this.isLeftNavClicked"
          v-bind:src="require(`@/assets/img/${leftNavImgsrc}`)" 
          width="30px">
        </b-img>
       </div>
     </div>

    </div>
    <!-- 내용 끝 -->


  </div>

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
          <b-img type="image" @click="searchA(search_input_text, search_input_period)" style="cursor:pointer" v-bind:src="require(`@/assets/img/icons8-search-50.png`)" width="25px"></b-img>
          <input type="text" v-on:keyup.enter="searchA(search_input_text, search_input_period)" v-model="search_input_text" value=""  autocomplete="off" autofocus title="회사명으로 검색해보아요~" class="search_top_text" >
          <input readonly type="text" v-model="search_input_period" value=""  autocomplete="off" autofocus title="기간선택해보아요~" class="search_top_period" >
          <b-img type="image" @click="calendar()" style="cursor:pointer" v-bind:src="require(`@/assets/img/icons8-calendar-50.png`)" width="25px"></b-img>
        </div>
        </form>
      </div>
      
      <hr class="featurette-divider">
    <!--/ 검색 창 -->
   
   
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


      <div v-for="(apply,idx) in apply_lists" :key="apply.apid">
             <div class="custom-contents" >
            
              <b-carousel-slide
                  caption="First slide"
                  text="Nulla vitae elit libero, a pharetra augue mollis interdum."
                  img-src= "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbbxeSR%2FbtqGJFWlSNZ%2FkiqfkXc5BjdCCqOKQx2yKK%2Fimg.jpg" >

               
                      <!-- 제목 -->
                        <div>
                            <h2>{{apply.apCompany}}</h2>
                        </div>
                        <div>
                            <h4>{{apply.apTerm}}</h4>
                        </div>

                      <!-- 날짜 -->
                        <div class="date-align">
                          <small>{{apply.ap_term}}</small>
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
  <!-- /케러셀 -->



    <!-- 목록 디테일 -->



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
                  <small>{{selected_apply.ap_term}}</small>
                </div>

              <!-- 내용 -->
              <div>
                  <p class="txt_line"> {{selected_apply.apDesc}} </p>
              </div>
            </div>

        </div><!-- /지원목록 -->

  <!-- 임시 지원목록 리스트 -->




<!-- 목록 리스트 -->
<div v-for="(apply,idx) in apply_lists" :key="apply.apid">
    

      <!-- 목록 요약 -->
      <div class="custom-temp col-md-12 list-top">
          <div class="custom-contents2">
                <!-- 제목 -->
                  <div>
                      <h2>{{apply.apCompany}}</h2>
                  </div>
                  <div>
                      <h4>{{apply.apTerm}}</h4>
                  </div>

                <!-- 날짜 -->
                  <div class="date-align">
                    <small>{{apply.ap_term}} </small>
                  </div>

                <!-- 내용 -->
                <div>
                    <p class="txt_line"> {{apply.apDesc}} </p>
                </div>
          </div>
          
      <!-- 버튼 -->
              <div class="col-md-1">
                  <p class="icon-right" style="margin-bottom: 0;">
                    <b-img
                    v-if="isEditClicked_list[idx]"
                      @click="clickeEdit(apply.apid, idx)"
                      style="cursor:pointer"
                      v-bind:src="require(`@/assets/img/icons8-trash-24.png`)"
                      width="15px"
                    ></b-img>

                    <b-img
                    v-if="!isEditClicked_list[idx]"
                      @click="clickeEdit(apply.apid, idx)"
                      style="cursor:pointer"
                      v-bind:src="require(`@/assets/img/icons8-pencil-24.png`)"
                      width="15px"
                    ></b-img>


                    <b-img
                      @click="deleteA(apply.apid, idx)"
                      style="cursor:pointer"
                      v-bind:src="require(`@/assets/img/icons8-trash-24.png`)"
                      width="15px"
                    ></b-img>

                    
                    <!--숨기는 용, 클릭에 반응하게하려고 -->
                    <div style="display:none"  > #{{isEditCheck}}</div>
  
                  </p>
              </div>
      <!--/버튼-->

      </div>
      <!--/목록 요약-->
 

    <!-- 목록 디테일 -->
   <div class="apply_detail">
   <b-collapse :id="`collapse_navbar2-${apply.apid}`" accordion="my-accordion" >
      <b-navbar class="custom-pageMenue">
        <div class="left-ex">
           경험

              <Board :id="'board-3' + apply.apid">
     
             <div   v-for="(ex, exid) in nav_ex_inlist" :key="ex.exid">
                  <Card  :id="'card-e-' + ex.exid" draggable="true">
                   <div >{{ex.title}}</div>
                  </Card>
              </div>
              
            
            </Board>




        </div>
     
         <div class="right-port">
           프로젝트

                <Board :id="'board-4' + apply.apid">
                      <div  v-for="(port, pid) in nav_port_inlist" :key="port.pid">
                        <Card :id="'card-p-' + port.pid" draggable="true">
                    
                          <div>{{port.title}}</div>
                        </Card>
                  
                      </div>
                      
              </Board>




        </div>
      </b-navbar>
   </b-collapse>
      <div @click="detailClickeIndex(apply.apid)">
        <b-img 
          class="custom-apply-Btn" 
          v-b-toggle= "`collapse_navbar2-${apply.apid}`" 
          style="cursor:pointer" 
          v-bind:src="require(`@/assets/img/icons8-view-more-30.png`)" 
          width="30px">
        </b-img>
      </div>
    </div>
    </div>
    <!-- 목록 디테일 -->

  </div>
  <!-- 목록리스트-->



  <!-- 추가하기 버튼 -->
    <div class="row">
      <div class=" col-button-custom">
        <div>
          <b-img
            v-on:click="addApply"
            :src="require(`@/assets/img/icons8-plus-50.png`)"
            width="60px"
          ></b-img>
        </div>
      </div>
    </div>
  <!-- /추가하기 버튼-->

  
  </div>
  <!-- Container 끝-->  

    

    
</div>
</template>

<script>
import constants from "../../lib/constants.js";
import axios from "axios";
import Vue from "vue";
import DragDrop from 'vue-drag-n-drop'
import dragula from 'dragula';

import Board from './Board'
import Card from './Card'
import EventBus from './EventBus';

//const SERVER_URL = "http://localhost:8080";

export default {
  name: "ManageApply",
  display: "Clone",
  order: 2,
  components: {
     DragDrop,
     Board,
     Card
  },
  data: () => {
    return {
        slide: 0,
        sliding: null,
        uid:"",

        periods: [],
        is_show:false,
        search_input_period:"미정",
        search_input_text:"",

        apply_lists:[],
        selected_apply:[],

        isLeftNavClicked:false,
        leftNavImgsrc:"icons8-arrow-right-64.png",
        intervalid1:"",
        intervalid2:"",

        nav_port_list:[],
        nav_ex_list:[],
        nav_port_inlist:[],
        nav_ex_inlist:[],
        isEditClicked_list:[],
        isEditCheck:false,

        flowersImg : [ 
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbNQI5h%2FbtqGJuUCIN5%2FemfrZIKbSQvU9AYp9xXWhK%2Fimg.jpg",
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbmO10q%2FbtqGSsg736Q%2F2Vk8PtXWwwroClNtBaR7lk%2Fimg.jpg",
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcBif9C%2FbtqGNC5EItY%2FwEwvIFxMncmII3L0G4EBKK%2Fimg.jpg",
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdHQToz%2FbtqGJwdLhDV%2F99NvaPkVv90sofZ4mXG2Vk%2Fimg.jpg",
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcIngX9%2FbtqGJwx6UEi%2F1PANxFjnZjWGq8NSUIuljK%2Fimg.jpg",
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FJEurJ%2FbtqGK4ajX2J%2Fj5FxtSbXqtz2qJA8nqZ8Ik%2Fimg.jpg",
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fcbqw1R%2FbtqGK4g25HF%2FePZlqlieqAV9yWoJRqNp90%2Fimg.jpg",
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbbxeSR%2FbtqGJFWlSNZ%2FkiqfkXc5BjdCCqOKQx2yKK%2Fimg.jpg" ]
        ,
        temp : "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbbxeSR%2FbtqGJFWlSNZ%2FkiqfkXc5BjdCCqOKQx2yKK%2Fimg.jpg"  
    };
  }
  ,
  computed:{
    
  },
  watch:{
  },

  mounted(){ 
  }
,
  created() {
    //이벤트 버스
      EventBus.$on('test', (payload)=>{
            this.msg = payload;
            alert.log(this.msg);
        });
  
     //지원기간 가져오기, 없을경우만
    if(constants.APPLY_PERIOD == null){
      axios
        .get(this.$SERVER_URL + `/period`, {
        
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

        //수정하기 클릭햇냐
        this.isEditClicked_list=[];
        for(var i=0; i<this.apply_lists.length; i++){
          this.isEditClicked_list.push(false);
        }

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
   /*  view_apply_detail(in_select_apply){
      alert("임시");
      this.selected_apply = in_select_apply;
    }, */
    isEmptyApply(){
      if(this.selected_apply == ""){
        return false;
      }
      return true;
    },
    changeLeftImg(){
      this.intervalid1 = setTimeout(() => {
                   
          if(this.isLeftNavClicked){
            this.leftNavImgsrc ="icons8-arrow-right-64.png"
          }else{
            this.leftNavImgsrc ="icons8-arrow-left-64.png"
          }
          this.isLeftNavClicked = !this.isLeftNavClicked;
            }, 80);
    },

   getComponentData() {
      return {
        on: {
          change: this.handleChange,
          input: this.inputChanged
        },
        attrs:{
          wrap: true
        },
        props: {
          value: this.activeNames
        }
      }
   },
    log: function(evt) {
      window.console.log(evt);
    },
    searchA: function(text, period){
        
          if(period == "미정") period = "";

          axios
          .get(this.$SERVER_URL + `/apply/search`, {
            params: {
              uid: localStorage["uid"],
              searchTxt : text,
              period : period
            },
          })
          .then((response) => {
            
            console.log(response)
            console.log(response.data.object);
            this.apply_lists = response.data.object;
            console.log(response.data.object[0].portfolioTags);

            //수정하기 클릭햇냐
            this.isEditClicked_list=[];
          for(var i=0; i<this.apply_lists.length; i++){
            this.isEditClicked_list.push(false);
          }

            
          })
          .catch((error) => {
            console.log(error);   
          });


    },
    detailClickeIndex:function(apid){
      //alert(apid);

      //네비에 없는 프로젝트가져오기, 그다음 경험가져옴
      //같이가져오니까 하나만가져오더라고;;
      this.getPortOutNav(apid);           

      this.getPortInNav(apid);
    },

    getPortOutNav:function (apid) {
       axios
        .get(this.$SERVER_URL + `/apply/outportfolio`, {params: {
              uid: localStorage["uid"],
              apid: apid
            }})
        .then((response) => {

        //alert("넵 ec")
         //this.nav_port_list.splice(0,this.nav_port_list.length+1);
         this.nav_port_list = response.data.object;
         console.log(response.data.object);
         
          this.getExOutNav(apid);
        })
        .catch((error) => {
        });
      
    },

    getExOutNav:function (apid) {
      axios
        .get(this.$SERVER_URL + `/apply/outexp`, {params: {
              uid: localStorage["uid"],
              apid: apid
            }})
        .then((response) => {
          //alert("넵 port")
         this.nav_ex_list = response.data.object;
        console.log(response.data.object);
        
        })
        .catch((error) => {
        });
    },
    getPortInNav:function (apid) {
       axios
        .get(this.$SERVER_URL + `/apply/inportfolio`, {params: {
              uid: localStorage["uid"],
              apid: apid
            }})
        .then((response) => {

        //alert("넵 ec")
         //this.nav_port_list.splice(0,this.nav_port_list.length+1);
         this.nav_port_inlist = response.data.object;
         console.log(response.data.object);
         
          this.getInOutNav(apid);
        })
        .catch((error) => {
        });
      
    },
    getExInNav:function (apid) {
      axios
        .get(this.$SERVER_URL + `/apply/inexp`, {params: {
              uid: localStorage["uid"],
              apid: apid
            }})
        .then((response) => {
          //alert("넵 port")
         this.nav_ex_inlist = response.data.object;
        console.log(response.data.object);
        
        })
        .catch((error) => {
        });
    },
    addApply:function(){

       axios
        .post(this.$SERVER_URL + `/apply`, {
          apCompany: "제목",
          apDesc:"설명",
          apTerm:"미정",
          uid: localStorage["uid"],
        })
        .then((response) => {

          //표면상 추가
          this.apply_lists.push(response.data.object);
        })
        .catch((error) => {
          //alert("실패");
          console.log(error);
        });


    },
    clickeEdit:function(apid, idx){
      //alert(apid + " - " + idx);

      this.isEditClicked_list[idx] = !this.isEditClicked_list[idx];
      this.isEditCheck = !this.isEditCheck;
    },
    deleteA:function(apid, idx){
      axios
        .delete(this.$SERVER_URL + `/apply`, {params: {
              uid: localStorage["uid"],
              apid: apid
            }})
        .then((response) => {
          //alert("넵 port")
        this.$delete(this.apply_lists, idx);
        
        })
        .catch((error) => {
        });
    },
    test2:function(value){
        alert(value);
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
    background: #e7e7e7ff;
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
    background: #e7e7e7ff;
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
   margin-top: 5%;

  border-top: 1.5px solid #22222222;
  border-left: 1.5px solid #22222222;
  border-right: 1.5px solid #22222222;
  border-bottom: 1.5px solid #22222222;
  box-shadow: 0 0 4px rgb(111, 111, 111);

}


.apply_detail {
    text-align: center;
    position: inherit;
    margin: auto;
    top: 0;
    /* right: auto; */
    width: 1024;
    border: 0;
    z-index: 10;
}

.custom-apply-Btn{

  position: relative;
    top: 2px;
}

.custom-pageMenue{
  border-bottom-right-radius: 8px;
    border-bottom-left-radius: 8px;
    background-color: #eeeeee;
    color: white;

    opacity: 0.6;

   margin-right: auto;
      
    border-top: 1.5px solid #22222222;  
    border-left: 1.5px solid #22222222;
    border-right: 1.5px solid #22222222;
    border-bottom: 1.5px solid #22222222;
    box-shadow: 0 0 4px rgb(111, 111, 111);
    

}

.left-ex{
  text-align: center;
  margin: auto;
  border-right: 1.5px solid #22222222;
  color: #000;
  min-height: 10rem;
  width: 50%;
}

.right-port{
  text-align: center;
  min-height: 10%;
  color: #000;
  min-height: 10rem;
 width:   50%;
}

.temp{
      display: -webkit-box;
      position: fixed;
      z-index: 11;
}

.custom-pageMenue-right{
  border-bottom-right-radius: 30px;
  border-top-right-radius: 30px;
    background-color: #eeeeee;
    color: white;

    opacity: 0.9;
   min-height: 50rem;
   min-width: 7rem;
   width: 50%;

      
    border-left: 1.5px solid #22222222;
    border-right: 1.5px solid #22222222;
    border-bottom: 1.5px solid #22222222;
    box-shadow: 0 0 4px rgb(111, 111, 111);
    

}

.custom-nav-right-contents{
  display: flex;
   margin-right: auto;
      
}

.right_btn{
  transform: translate(0%, 50%);
          min-height: 50rem;
          
}

.contents_list{
  display: inline;
}

.custom-left {
  position: relative;
 }

 .custom-left-list{
  height: 100%;  
 }

 .list-top{
   display: flex;
 }

 .custom-contents2{
   width: 100%;
 }

.left {float: left;position: relative;width: 50%;height: 100%; } 
.right {float: left;position: relative;width: 40%;margin-left: 5%;height: 100%; } 
#display {background: #2d2d2d;border: 10px solid #000000;border-radius: 5px;font-size: 2em;color: white;height: 100px;min-width:200px;text-align: center;padding: 1em;display:table-cell;vertical-align:middle; } 
#drop-target {border: 2px dashed #D9D9D9;border-radius: 5px;min-height: 50px;margin: 0 auto;margin-top: 10px;padding: 2em;display: block;text-align: center; } 
#drop-target > div {text-align: center;float: left;padding: 1em;margin: 0 1em 1em 0;box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.3);border-radius: 100px;border: 2px solid #ececec;background: #F7F7F7;transition: all .5s ease; } 
#drop-target > div:active {-webkit-animation: wiggle 0.3s 0s infinite ease-in;animation: wiggle 0.3s 0s infinite ease-in;opacity: .6;border: 2px solid #000; } @-webkit-keyframes wiggle {0% {-webkit-transform: rotate(0deg);}25% {-webkit-transform: rotate(2deg);}75% {-webkit-transform: rotate(-2deg);}100% {-webkit-transform: rotate(0deg);} } @keyframes wiggle {0% {transform: rotate(-2deg);}25% {transform: rotate(2deg);}75% {transform: rotate(-2deg);}100% {transform: rotate(0deg);} } 
.gu-mirror {position: fixed !important;margin: 0 !important;z-index: 9999 !important;padding: 1em; } .gu-hide {display: none !important; } .gu-unselectable {-webkit-user-select: none !important;-moz-user-select: none !important;-ms-user-select: none !important;user-select: none !important; } 
.gu-transit {opacity: 0.5;-ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=50)";filter: alpha(opacity=50); } .gu-mirror {opacity: 0.5;-ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=50)";filter: alpha(opacity=50); }

.custom-drag-elements {display: inline-block;  height:100%; background-color: #dfdfdf;border-radius: 5px;min-height: 50px;margin: 0 auto;padding: 2em; } 
.custom-drag-elements > div { width:40%; text-align: center;float: left;padding: 1em;margin: 0 1em 1em 0;box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.3);border-radius: 100px;border: 2px solid #ececec;background: #F7F7F7;transition: all .5s ease; } 
.custom-drag-elements > div:active {-webkit-animation: wiggle 0.3s 0s infinite ease-in;animation: wiggle 0.3s 0s infinite ease-in;opacity: .6;border: 2px solid #000; } 
.custom-drag-elements > div:hover {border: 2px solid #9c85ff;background-color: #7a63ff; } 


.flexbox{
  display: flex;
  justify-content: space-between;

  width: 100%;
  max-width: 768px;
  height: 50vh;

  overflow: hidden;

  margin: 0 auto;
  padding: 15px;

}

.flexbox .board{
  display: flex;
  flex-direction: column;

  width: 100%;
  max-width: 300px;

  background-color: #313131;

  padding: 15px;

}

.flexbox .board .card{
  padding: 15px 25px;
  background-color: #F3F3F3;

  cursor: pointer;
  margin-bottom: 15px;
}

.flexbox .board .card p{
  color:#000000
}

 .board{
  display: flex;
  flex-direction: column;

  width: 100%;
  max-width: 300px;

  background-color: #313131;

  padding: 15px;

}

 .board .card{
  padding: 15px 25px;
  background-color: #F3F3F3;

  cursor: pointer;
  margin-bottom: 15px;
}

 .board .card div{
  color:#000000
} 



</style>

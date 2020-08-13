<template>
  <div>
    <br>
    <br>


    <!-- <ul>
      <li v-for="tag in tags" :key="tag.tid" style="display: inline-block;" class="m-1">#{{ tag.tag_Name }}</li>
    </ul> -->

    <!-- 여기부터 -->
    <div class="tagButtons box-table2">
      <b-button-group size="sm">
        <div class="col-lg-12 ">
        <b-button
          pill
          v-for="(tag, idx) in tags"
          :key="idx"
          :pressed="tagState(tag)"
          @click="filtering(tag)"
          variant="secondary"
          style="display: inline-block; color: black;"
          class="m-1"
        >
          #{{ tag.tagName }}
        </b-button>
        </div>
      </b-button-group>
    </div>
    <b-container>
      <b-row align-h="between">
        <b-col cols="4">
          <span v-if="allTagState" @click="allTagOnOff" style="cursor: pointer; color: black;">태그 전체 선택 해제</span>
          <span v-else @click="allTagOnOff" style="cursor: pointer; color: black;">태그 전체 선택</span>
        </b-col>
        <b-col cols="4" style="text-align: right;">
          <span v-if="isIncludeNoTag" @click="showNotagProject" style="cursor: pointer; color: black;">태그없는 프로젝트 숨기기</span>
          <span v-else @click="showNotagProject" style="cursor: pointer; text-align: right; color: black;">태그없는 프로젝트 보여주기</span>
          <!-- <span v-if="isIncludeNoTag" @click="showNotagProject" style="cursor: pointer; color: black;">{{ showNoTag }}</span>
          <span v-else @click="showNotagProject" style="cursor: pointer; text-align: right; color: black;">{{ showNoTag }}</span> -->
        </b-col>
    </b-row>
    </b-container>
    <!-- 여기까지 -->

    <hr />

    <!-- <div
      v-for="portfolio in portfolios"
      :key="portfolio.pid"
      style="display: inline-block;"
      class="m-1"
    >
      <b-card :title="portfolio.title" style="max-width: 20rem; min-height: 17rem;" class="mb-2">
        <b-card-text>
          <br />
          {{ portfolio.start_date }} ~ {{ portfolio.end_date }}
          <hr />
          {{ portfolio.contents }}
          <hr />
          태그
          <ul>
            <li
              v-for="portfolioTag in portfolio.tag"
              :key="portfolioTag.tid"
              style="display: inline-block;"
              class="m-1"
            >
              #{{ portfolioTag.tagName }}
            </li>
          </ul>
        </b-card-text>
        <b-button href="#" variant="primary">다운로드(버튼, 미완)</b-button>
      </b-card>
    </div>
    <hr /> -->

    <!-- 위랑 동일 / 카드 형태만 다름 -->
    <b-container>
      <b-row align-v="start" class="ml-5">

          <div
            v-for="portfolio in portfolios"
            :key="portfolio.pid"
            style="display: inline-block;"
            class="columns is-multiline"
          >
            

            
            <b-card v-if="showProject(portfolio)" style="background: lightgrey; width:20rem; height:26rem;" class="m-2">
              <div>
                  
                <b-container>
                  <b-row align-h="between">
                    <b-col style="padding-left: 0;" cols="8">
                      <b-button size="sm" variant="outline-dark">download</b-button>
                      <b-button
                        @click="gotoDetail(portfolio.pid)"
                        size="sm"
                        variant="outline-dark"
                        >상세보기
                      </b-button>
                    </b-col>
                    <b-col style="text-align: right; padding-right: 0;" cols="4">
                      <b-img @click="deleteP(portfolio)" style="cursor:pointer;" v-bind:src="require(`@/assets/img/icons8-trash-24.png`)" width="15px"></b-img>
                    </b-col>
                  </b-row>
                </b-container>


                
                <h2 v-if="portfolio.title.length > 15">{{ portfolio.title.slice(0, 15) }}...</h2>
                <h2 v-else>{{ portfolio.title }}</h2>
                
                <small style="display: inline;">{{ portfolio.start_date }} ~ {{ portfolio.end_date }}</small>
                <!-- <div style="float: right; display: inline;"><b-button size="sm" variant="outline-dark">download</b-button></div> -->
                <p
                  v-if="portfolio.contents.length > 60"
                  class="mt-2"
                >{{ portfolio.contents.slice(0, 60) }}...</p>
                <p v-else class="mt-2">{{ portfolio.contents }}</p>
                <!-- 태그 출력 -->
                <div v-if="portfolio.tag.length > 4">
                <div
                  v-for="portfolioTag in portfolio.tag.slice(0, 4)"
                  :key="portfolioTag.tid"
                  style="display: inline-block;"
                >
                  <h4>
                    <b-badge pill class="mr-3" id="tag" text-variant="black">#{{ portfolioTag.tagName }}</b-badge>
                  </h4>
                </div>...
                </div>
                <div v-else-if="portfolio.tag.length > 0">
                  <div
                  v-for="portfolioTag in portfolio.tag.slice(0, 4)"
                  :key="portfolioTag.tid"
                  style="display: inline-block;"
                >
                  <h4>
                    <b-badge pill class="mr-3" id="tag" text-variant="black">#{{ portfolioTag.tagName }}</b-badge>
                  </h4>
                </div>
                </div>
                <div v-else>태그를 추가해보세요</div>
              </div>
            </b-card>      
          </div>


      

      
      </b-row>

      <!-- (+) 버튼 -->
      <div class="row mt-3 mb-3">
        <div class="col-button-custom">
            <div>
              <b-img v-on:click="addProject" :src="require(`@/assets/img/icons8-plus-50.png`)" width="60px" v-bind:style = "buttonStyle" v-on:mouseover = "change_button" v-on:mouseout = "origin_button"></b-img>
            </div>
        </div>
      </div>

    </b-container>
  </div>
</template>

<script>
import constants from "../../lib/constants.js";
import axios from "axios";
import lodash from "lodash";

//const SERVER_URL = "http://localhost:8080";

export default {
  
  name: "ManagePortfolio",
  data: () => {
    return {
      tags: [],
      portfolios: [],
      portfolioTags: [],
      selectedTags: [],
      buttonStyle:{
        width :"",
        opacity: "",
      },
      isIncludeNoTag: true,
      allTagState: true,
    };
  },
  created() {
    if (!constants.IS_LOGED_IN) {
      this.$router.push({ name: constants.URL_TYPE.MAIN.NOLOGINHOME });
    };

    
    axios
      .get(this.$SERVER_URL + `/portfolio/Tags`, {
        params: {
          uid: localStorage["uid"],
        },
      })
      .then((response) => {
        // console.log('태그리스트');
        this.tags = response.data.object;

        // Array.prototype.forEach.call(this.tags, tag => 
        //   this.selectedTags.push(tag.tid)
        // )

        axios
      .get(this.$SERVER_URL + `/portfolio/all`, {
        params: {
          uid: localStorage["uid"],
        },
      })
      .then((response) => {
        // console.log(response.data.object);
        this.portfolios = response.data.object;
      })
      .catch((error) => {
        console.log(error);
      });
      })
      .catch((error) => {
        console.log(error);
      });

      
  },
  methods: {
    addProject() {
      var date = new Date();
      var year = date.getFullYear();
      var month = date.getMonth()+1;
      var day = date.getDate();

      if(day<10) day = "0"+day;
      if(month<10) month = "0"+month;
       
      var startdate = year+"-"+month+"-"+day;

      axios
            .post(this.$SERVER_URL + `/portfolio`, {
              contents: "내용",
              title:"제목",
              uid: localStorage["uid"],
            })
            .then((response) => {
              response.data.object.start_date = startdate;
              response.data.object.end_date = startdate;
              response.data.object.state = false;
              response.data.object.tag = [];
              this.portfolios.push(response.data.object);
            })
            .catch((error) => {
              console.log(error); 
            });
    },
    change_button: function(){
      this.buttonStyle.opacity = "1";
      this.buttonStyle.width = "61px";
    },
    origin_button: function(){
      this.buttonStyle.opacity = "0.6";
      this.buttonStyle.width = "60px";
    },

    showProject(tagInPortfolio) {
      var selectedTags = this.selectedTags;
      var cnt = 0;
      tagInPortfolio.tag.forEach(function(tag) {
        if (selectedTags.includes(tag.tid)) {
          cnt ++;
        };
        return cnt;
      });
      if (cnt == selectedTags.length || tagInPortfolio.tag.length == 0 && this.isIncludeNoTag) {
        return true
      } else {
        return false
      }
    },

    filtering(tag) {
      tag.state = !tag.state;
      if (this.selectedTags.includes(tag.tid)) {
        this.selectedTags.splice(this.selectedTags.indexOf(tag.tid), 1)
      } else {
        this.selectedTags.push(tag.tid)
      }
    },

    showNotagProject() {
      this.isIncludeNoTag = !this.isIncludeNoTag
      if (this.isIncludeNoTag == true) {
        axios
        .get(this.$SERVER_URL + `/portfolio/all`, {
          params: {
            uid: localStorage["uid"],
          },
        })
        .then((response) => {
          // console.log(response.data.object);
          this.portfolios = response.data.object;
        })
        .catch((error) => {
          console.log(error);
        });
      } else {
        let tmp = []
        Array.prototype.forEach.call(this.portfolios, portfolio => {
          if (portfolio.tag.length > 0) {
            tmp.push(portfolio)
          }
        })
        this.portfolios = tmp
      }
    },


    deleteP: function(portfolio){      
        axios
      .delete(this.$SERVER_URL + `/portfolio/${portfolio.pid}`)
      .then((response) => {
        // this.$delete(this.portfolios, portfolio);
        this.portfolios.splice(this.portfolios.indexOf(portfolio), 1)
        // this.getTag();
        //alert("삭제완료 " + experience.exid);
        axios
      .get(this.$SERVER_URL + `/portfolio/Tags`, {
        params: {
          uid: localStorage["uid"],
        },
      })
      .then((response) => {
        this.tags = response.data.object;

        // Array.prototype.forEach.call(this.tags, tag => 
        //   this.selectedTags.push(tag.tid)
        // )

      })
      .catch((error) => {
        console.log(error);
      });

      axios
      .get(this.$SERVER_URL + `/portfolio/all`, {
        params: {
          uid: localStorage["uid"],
        },
      })
      .then((response) => {
        console.log(response.data.object);
        this.portfolios = response.data.object;
      })
      .catch((error) => {
        console.log(error);
      });
      })
      
      .catch((error) => {
        console.log(error); 
      });
    },
    tagState(tag) {
      return tag.state;
    },
    allTagOnOff() {
      this.allTagState = !this.allTagState;
      if (this.allTagState == false) {
        Array.prototype.forEach.call(this.tags, tag => 
          tag.state = this.allTagState,
          this.selectedTags.push(tag.tid))
      } else {
        this.selectedTags = []
        Array.prototype.forEach.call(this.tags, tag => 
          tag.state = this.allTagState)
      }
    },
    gotoDetail(pid) {
      // console.log(pid);
      this.$router.push({path:`/PortfolioDetails/${pid}`});
    },
  },
  computed: {
    // showNoTag: function() {
    //   if (this.isIncludeNoTag == true) {
    //     return "태그없는 프로젝트 숨기기"
    //   } else {
    //     return "태그없는 프로젝트 보여주기"
    //   }
    // }
  },
}

</script>

<style>
.tagButtons{
  justify-content: center;
  align-items: center;
  text-align: center;
}
.col-button-custom{
  margin-left: auto;
  margin-right: auto;  
}

.img-custom{
    text-align: right;
}

.box-table2{
    border: 1px solid #888888;
    box-shadow: 0 0 2px rgb(111, 111, 111);
    margin: 1%;
    padding: 2%;
    background: #eeeeee;
    opacity: 0.8
}
</style>
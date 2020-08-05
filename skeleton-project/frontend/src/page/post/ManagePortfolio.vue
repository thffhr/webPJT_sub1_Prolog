<template>
  <div>
    <h1 style="text-align: center;">포트폴리오 관리 페이지</h1>
    <h2 style="text-align: center;">태그들 보여줘야해</h2> 


    <!-- <ul>
      <li v-for="tag in tags" :key="tag.tid" style="display: inline-block;" class="m-1">#{{ tag.tag_Name }}</li>
    </ul> -->

    <!-- 여기부터 -->
    <div class="tagButtons">
      <b-button-group size="sm">
        <button v-if="isIncludeNoTag" @click="showNotagProject">태그없는 프로젝트 숨기기</button>
        <button v-else @click="showNotagProject">태그없는 프로젝트 보여주기</button>
        <b-button
          v-for="(tag, idx) in tags"
          :key="idx"
          :pressed.sync="tag.state"
          @click="filtering(tag)"
          variant="primary"
          style="display: inline-block; text-color: black;"
          class="m-1"
        >
          #{{ tag.tag_Name }}
        </b-button>
      </b-button-group>
    </div>
    <!-- 여기까지 -->
    <!-- <button @click="allTagOnOff">태그 전체 켜기/끄기</button> -->

    <hr />
    <h2 style="text-align: center;">내 포트폴리오</h2>

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
      <b-row align-v="start" align-h="center">
        
        <div
          v-for="portfolio in portfolios"
          :key="portfolio.pid"
          style="display: inline-block; max-width: 20rem; min-height: 17rem;"
          class="m-1"
        >
          <b-card class="mb-2" v-if="showProject(portfolio)" cols="4">
            <div class="m-3" style="border: solid;">
              <h2>{{ portfolio.title }}</h2>
              <small>{{ portfolio.start_date }} ~ {{ portfolio.end_date }}</small>
              <p
                class="mt-2"
              >{{ portfolio.contents }}</p>
              <!-- 태그 출력 -->
              <div
                v-for="portfolioTag in portfolio.tag"
                :key="portfolioTag.tid"
                style="display: inline-block;"
              >
                <h4>
                  <b-badge pill class="mr-3" id="tag" text-variant="black">#{{ portfolioTag.tagName }}</b-badge>
                </h4>
              </div>
            </div>
          </b-card>        
          <hr>
        </div>
      </b-row>

      <!-- (+) 버튼 -->
      <div class="row">
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
        this.tags = response.data.object;

        Array.prototype.forEach.call(this.tags, tag => 
          this.selectedTags.push(tag.tid)
        )
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
      var selectedTags = this.selectedTags
      var cnt = 0
      tagInPortfolio.tag.forEach(function(tag) {
        if (selectedTags.includes(tag.tid)) {
          cnt ++;
        };
        return cnt;
      });
      if (cnt>0 || tagInPortfolio.tag.length == 0 && this.isIncludeNoTag) {
        return true
      } else {
        return false
      }
    },

    filtering(tag) {
      if (this.selectedTags.includes(tag.tid)) {
        this.selectedTags.splice(this.selectedTags.indexOf(tag.tid), 1)
      } else {
        this.selectedTags.push(tag.tid)
      }
    },

    showNotagProject() {
      this.isIncludeNoTag = !this.isIncludeNoTag
    },

    allTagOnOff() {
      if (this.allTagState == true) {
        this.selectedTags = []
        this.allTagState = false
        Array.prototype.forEach.call(this.tags, tag => 
              tag.state = false
            )
      } else {
        axios
          .get(this.$SERVER_URL + `/portfolio/Tags`, {
            params: {
              uid: localStorage["uid"],
            },
          })
          .then((response) => {
            this.tags = response.data.object;

            Array.prototype.forEach.call(this.tags, tag => 
              this.selectedTags.push(tag.tid)
            )
          })
          .catch((error) => {
            console.log(error);
         });
        this.allTagState = true
        Array.prototype.forEach.call(this.tags, tag => 
              tag.state = true
            )
      }
    }
  },
  // 버튼
  computed: {
    tagStates() {
        return this.tags.map(tag => tag.state)
      },
    
  },
};
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
</style>
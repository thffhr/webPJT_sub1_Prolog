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
          :pressed.sync="tag.state"
          @click="filtering(tag)"
          variant="secondary"
          style="display: inline-block; text-color: black;"
          class="m-1"
        >
          #{{ tag.tag_Name }}
        </b-button>
        </div>
      </b-button-group>
    </div>
    <div class="mt-3" style="text-align: right; margin-right: 10rem;">
      <span v-if="isIncludeNoTag" @click="showNotagProject" style="cursor: pointer">태그없는 프로젝트 숨기기</span>
      <span v-else @click="showNotagProject" style="cursor: pointer">태그없는 프로젝트 보여주기</span>
    </div>
    <!-- 여기까지 -->
    <button @click="allTagOnOff">태그 전체 켜기/끄기</button>

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
          <b-card v-if="showProject(portfolio)" style="background: lightgrey; width:20rem; height:15rem;" class="m-2">
            <div>
              <!-- 삭제 img -->
              <div class="img-custom">
              <b-img align-h="end"  @click="deleteP(portfolio)" style="cursor:pointer; test-align: right;" v-bind:src="require(`@/assets/img/icons8-trash-24.png`)" width="15px"></b-img>
              </div>
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
              <div style="text-align: end;"><b-button size="sm" variant="outline-dark">download</b-button></div>
            </div>
          </b-card>      
          <!-- <hr> -->

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
    },

    deleteP: function(portfolio){      
        axios
      .delete(this.$SERVER_URL + `/portfolio/${portfolio.pid}`)
      .then((response) => {
        this.$delete(this.portfolios, portfolio);
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
      })
      
      .catch((error) => {
        console.log(error); 
      });
    },
  },
  // 버튼
  computed: {
    tagStates() {
        return this.tags.map(tag => tag.state)
      },
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
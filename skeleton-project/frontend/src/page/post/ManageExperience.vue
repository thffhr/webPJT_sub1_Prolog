<template>
  <div class="container">
    <!-- Three columns of text below the carousel -->
    <!-- 모든 태그들 -->
    <div class="row box-table">
      <div
        class="adge tag-margin-custom mr-3 badge-secondary badge-pill"
        v-for="tag in tags"
        :key="tag.tid"
      >
        <div v-if="showTag(tag)">
          #{{ tag.tagName }}
          <b-img
            @click="filtering(tag)"
            :pressed.sync="tag.state"
            
            variant="primary"
            style="cursor:pointer"
            v-bind:src="require(`@/assets/img/${tag.imgsrc}`)"
            width="20px"
          ></b-img>
        </div>
      </div>
      <!-- /.col-lg-2 -->
    </div>
    <!-- /.row -->
    <div class="noTag mt-3 mb-3">
      #No태그 여부
      <b-img
        @click="exeptNoTagClickE()"
        style="cursor:pointer"
        :pressed.sync="this.isIncludeNoTag"
        v-bind:src="require(`@/assets/img/${NoTagImgSrcT}`)"
        width="20px"
      ></b-img>
    </div>


    <!-- 경험목록 -->
    <div id="experienceList">
      <div v-for="(experience, exid) in experiences" :key="experience.exid">
        <div class="card-copy" v-if="showProject(experience)">
          <div class="row card-body">
            <div class="col-custom row-custom" style="display:inline-flex">
              <!-- 동그라미 -->
              <div v-bind:style="mystyle" class="img-circle col-md-2">
                <div class="content">
                  <div
                    v-for="experienceTag in experience.tags"
                    :key="experienceTag.tid"
                  >
                    #{{ experienceTag.tagName }}
                  </div>
                </div>
              </div>

              <div class="col-md-9 col-cotents">
                <!-- 제목 -->
                <p v-if="experience.clicked">
                  <input
                    v-model="experience.title"
                    @blur="experience.clicked = true"
                  />
                </p>
                <div v-else>
                  <h2>{{ experience.title }}</h2>
                </div>

                <!-- 날짜 -->
                <p v-if="experience.clicked">
                  <input
                    class="txt_line"
                    placeholder="2020-00-00"
                    v-model="experience.startdate"
                    @blur="
                      experience.clicked = true;
                      $emit('update');
                    "
                    @keyup.enter="$emit('update')"
                  />
                  ~
                  <input
                    class="txt_line"
                    placeholder="2020-00-00"
                    v-model="experience.enddate"
                    @blur="
                      experience.clicked = true;
                      $emit('update');
                    "
                    @keyup.enter="$emit('update')"
                  />
                </p>
                <div class="date-align" v-else>
                  <small
                    >{{ experience.startdate }} ~
                    {{ experience.enddate }}</small
                  >
                </div>
                <!-- 내용 -->
                <p v-if="experience.clicked">
                  <textarea
                    style="width:100%"
                    value="기본내용"
                    placeholder="Contents"
                    v-model="experience.contents"
                    @blur="
                      experience.clicked = true;
                      $emit('update');
                    "
                    @keyup.enter="$emit('update')"
                  >
                  </textarea>
                </p>
                <div v-else>
                  <p class="txt_line">{{ experience.contents }}</p>
                </div>

                <!-- 태그 -->
                <div class="editor_tag" v-if="experience.clicked">
                  <!-- exid도잇어야함 -->
                  <div
                    v-for="(experienceTag, tid) in experience.tags"
                    :key="experienceTag.tid"
                  >
                    <span class="txt_tag">
                      <span>#</span>
                      <span>{{ experienceTag.tagName }}</span>
                      <b-img
                        @click="
                          deleteTag(
                            experience.tags,
                            experienceTag,
                            tid,
                            experience
                          )
                        "
                        style="width:18px; height:18px; cursor:pointer"
                        v-bind:src="
                          require(`@/assets/img/icons8-close-window-50.png`)
                        "
                      >
                        <span>삭제</span>
                      </b-img>
                    </span>
                  </div>

                  <span class="inp_tag">
                    <span>#</span>
                    <div
                      style="inline-block"
                      value="태그"
                      placeholder="Tag입력"
                    >
                      <input
                        id="tagText"
                        v-model="tagText"
                        v-on:keyup.enter="
                          addTag(experience.tags, experience.exid, tagText)
                        "
                        type="text"
                        class="tf_g"
                        placeholder="태그입력"
                        style="box-sizing: content-box; width: 50px;"
                      />
                    </div>
                  </span>
                </div>
              </div>

              <!--버튼-->
              <div class="col-md-1">
                <p class="icon-right" style="margin-bottom: 0;">
                  <b-img
                    @click="clickeEdit(experience)"
                    :pressed.sync="experience.clicked"
                    style="cursor:pointer"
                    v-bind:src="require(`@/assets/img/${experience.imgsrc}`)"
                    width="15px"
                  ></b-img>
                  <b-img
                    @click="deleteE(exid, experience)"
                    style="cursor:pointer"
                    v-bind:src="require(`@/assets/img/icons8-trash-24.png`)"
                    width="15px"
                  ></b-img>
                </p>
              </div>
            </div>
            <!-- /.col-lg-4 -->
          </div>
        </div>
      </div>
      <!-- /.row -->
    </div>
    <!-- 경험목록 끝 -->

    <!-- (+) 버튼 -->
    <div class="row">
      <div class=" col-button-custom">
        <div>
          <b-img
            v-on:click="addExp"
            :src="require(`@/assets/img/icons8-plus-50.png`)"
            width="60px"
            v-bind:style="buttonStyle"
            v-on:mouseover="change_button"
            v-on:mouseout="origin_button"
          ></b-img>
        </div>
      </div>
      <!-- /.col-lg-4 -->
    </div>
    <!-- /.row -->

      <!-- START THE FEATURETTES -->

      <hr class="featurette-divider">

    <div class="row featurette">
      <div class="col-md-7">
        <h2 class="featurette-heading">
          경험을 적어보세요~!
          <span class="text-muted">반드시 도움이 될거에요.</span>
        </h2>
        <p class="lead">
          Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id
          ligula porta felis euismod semper. Praesent commodo cursus magna, vel
          scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.
        </p>
      </div>
      <div class="col-md-5">
        <img
          class="featurette-image img-responsive center-block"
          src="https://t1.daumcdn.net/cfile/tistory/21151B4E53E83DAF2C"
          alt="Generic placeholder image"
        />
      </div>
    </div>

    <hr class="featurette-divider" />
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
      tagText: "",
      tags: [],
      uid : localStorage["uid"],
      experiences: [],
      noTagExperiences: [],
      selectedTags: [],
      isIncludeNoTag  : true,
      NoTagImgSrcT: "icons8-doorbell-50.png",
      ex:[],
      experienceTags: {
        "": [],
      },
      mystyle: {
        width :"",
        height : "",
        opacity: ""
      },
      buttonStyle:{
        width :"",
        opacity: ""
      }
    };
  },
  beforeCreate() {},
  computed: {
    nickNameState() {
      
    },
  },

  created() {
    //alert(this.$SERVER_URL + `/portfolio/all`);
    if (!constants.IS_LOGED_IN) {
      this.$router.push({ name: constants.URL_TYPE.MAIN.NOLOGINHOME });
    }

    axios
      .get(this.$SERVER_URL + `/experience/Tags`, {
        params: {
          uid: localStorage["uid"],
        },
      })
      .then((response) => {
        // alert(this.$SERVER_URL + `/experience/Tags`);
       console.log(response.data.object);
       //
        this.tags = response.data.object;
        Array.prototype.forEach.call(this.tags, (t) =>
          Object.assign(t, { imgsrc: "icons8-plus-64.png" })
        );
        /* 
        Array.prototype.forEach.call(this.tags, tag => 
          this.selectedTags.push(tag.tid)
        ) */

        console.log(this.tags);
      })
      .catch((error) => {
        console.log(error);
        this.tags = [];
        console.log("태그리스트 가져오기 실패");
      });

    axios
      .get(this.$SERVER_URL + `/experience/all`, {
        params: {
          uid: localStorage["uid"],
        },
      })
      .then((response) => {
        
        console.log(response);
        console.log(response.data.object);
        this.experiences = response.data.object;
        console.log(response.data.object[0].portfolioTags);

          Array.prototype.forEach.call(this.experiences, t =>
          Object.assign(t, {imgsrc:"icons8-pencil-24.png"}),
          )
        
      })
      .catch((error) => {
        console.log(error);   
          this.experiences = [];
      });
  },
  methods: {
    editTodo: function(todo) {
      this.editedTodo = todo;
    },
    changebgcolor: function() {
      this.mystyle.opacity = "0.7";
    },
    originalcolor: function() {
      this.mystyle.opacity = "0.6";
    },
    change_button: function() {
      this.buttonStyle.opacity = "1";
      this.buttonStyle.width = "61px";
    },
    origin_button: function() {
      this.buttonStyle.opacity = "0.6";
        this.buttonStyle.width = "60px";
    },

    exeptNoTagClickE: function() {
      //lert("바뀔 값" + this.isIncludeNoTag);
       this.isIncludeNoTag = !this.isIncludeNoTag;

    },

    showTag(tag) {
      if (tag.tagName == null) {
        return false;
      } else {
        return true;
      }
    },

    //하나의 프로젝트를 보여줄거?
    showProject(experience) {

      

      var selectedTags = this.selectedTags;
      var cnt = 0;
      experience.tags.forEach(function(tag) {
        if (selectedTags.includes(tag.tid)) {
          cnt++;
        }
      });

      if (cnt==selectedTags.length  || (experience.tags.length==0 && this.isIncludeNoTag)) {
          return true
        } else {
          return false
        }

    },


    getTag() {
      axios
        .get(this.$SERVER_URL + `/experience/Tags`, {
          params: {
            uid: localStorage["uid"],
          },
        })
        .then((response) => {
          // alert(this.$SERVER_URL + `/experience/Tags`);
          console.log(response.data.object);
          //
          this.tags = response.data.object;
          Array.prototype.forEach.call(this.tags, (t) =>
            Object.assign(t, { imgsrc: "icons8-plus-64.png" })
          );

          /*  Array.prototype.forEach.call(this.tags, tag => 
          this.selectedTags.push(tag.tid)
        ) */

          console.log(this.tags);
        })
        .catch((error) => {
          console.log(error);
          //alert("실패");
        });
    },
  
    
    filtering(tag) {
      //tag가 선택되어있다면
      if (this.selectedTags.includes(tag.tid)) {
        this.selectedTags.splice(this.selectedTags.indexOf(tag.tid), 1);
        tag.imgsrc = "icons8-plus-64.png";
      } else {
        //tag가 선택되어 있지 않다면
        this.selectedTags.push(tag.tid);
        tag.imgsrc = "icons8-close-window-26.png";
      }
      tag.state = !tag.state;
     
    },

    addExp: function(){      
      var date = new Date();
      var year = date.getFullYear();
      var month = date.getMonth()+1;
      var day = date.getDate();

      if(day<10) day = "0"+day;
      if(month<10) month = "0"+month;
       
      var startdate = year+"-"+month+"-"+day;
      
      //alert(this.uid);
        axios
      .post(this.$SERVER_URL + `/experience`, {
        title:"제목",uid:this.uid, 
      })
      .then((response) => {
        response.data.object.startdate = startdate;
        response.data.object.enddate = startdate;
        response.data.object.imgsrc = "icons8-pencil-24.png";

      var startdate = year + "-" + month + "-" + day;

        //경험이 아예없으면
        if(response.data.status == false)
        { 
          experiences = response.data.object;
        }
        else {
            response.data.object.tags = [];
          this.experiences.push(response.data.object);
          } 
          
      })
      .catch((error) => {
        //alert("실패");
        console.log(error); 
      });
    },


    deleteE: function(exid,experience){      
    
        axios
      .delete(this.$SERVER_URL + `/experience/${experience.exid}`)
      .then((response) => {
        //alert(exid);
        this.$delete(this.experiences, exid);
        this.getTag();
        //alert("삭제완료 " + experience.exid);
      })
      .catch((error) => {
        //alert("실패");
        console.log(error); 
      });
    },

    deleteE: function(exid, experience) {
      axios
        .delete(this.$SERVER_URL + `/experience/${experience.exid}`)
        .then((response) => {
          //alert(exid);
          this.$delete(this.experiences, exid);
          this.getTag();
          //alert("삭제완료 " + experience.exid);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    clickeEdit: function(experience) {
      experience.clicked = !experience.clicked;
      //저장하기
      if(!experience.clicked){ 
        this.editE(experience);
        experience.imgsrc = "icons8-pencil-24.png";
        }
      //수정하기
      else{ 
        experience.imgsrc = "icons8-save-close-64.png";
        
        }
      //alert(experience.clicked);
    },

    editE: function(experience) {
      axios
        .put(this.$SERVER_URL + `/experience`, {
          id: experience.exid,
          title: experience.title,
          uid: this.uid,
          contents: experience.contents,
          startdate: experience.startdate,
          enddate: experience.enddate,
        })
        .then((response) => {
          //alert("성공");
        })
        .catch((error) => {
          console.log(error);
        });
    },

    deleteTag: function(tags, experienceTag, idx, experience) {
      axios
        .delete(
          this.$SERVER_URL +
            `/experience/${experienceTag.tid}/${experience.exid}`
        )
        .then((response) => {
          //경험안에 태그 삭제
          tags.splice(idx, 1);

          //위에보이는 태그 삭제
          //만약 더있으면 삭제안함!(중복된애가있으면)
          var count = 0;
          //2중포문
          Array.prototype.forEach.call(
            this.experiences,
            (i) => {
              Array.prototype.forEach.call(i.tags, (j) => {
                if (experienceTag.tid == j.tid) {
                  count++;
                }
              }); //j
            } //i
          );

          //삭제했을때 0개가되면 위에 보여주는 태그도 삭제.
          if (count == 0) {
            var index = 0;
            Array.prototype.forEach.call(this.tags, (i) => {
              if (experienceTag.tid == i.tid) {
                this.tags.splice(index, 1);
                //alert(index);
              }
              index++;
            });
          }
        })
        .catch((error) => {
          console.log(error);
          alert(tag.tid + " - " + experience.exid);
          alert("삭제실패");
        });
    },
    addTag: function(tags, exid, tagText) {
      this.tagText = "";
      //alert(this.uid);
      axios
        .post(this.$SERVER_URL + `/tag`, {
          tagName: tagText,
        })
        .then((response) => {
          //조인테이블과 이어주기
          this.addTagLink(tags, exid, response.data.object.tid, tagText);

          //가장위쪽 태그테이블
          var res = Object.assign(response.data.object, {
            imgsrc: "icons8-plus-64.png",
          });

          //비어있으면 푸시에 에러남..
          //이미있으면 태그창에 추가안하기 위한 count
          var count = 0;
          Array.prototype.forEach.call(this.tags, (t) => {
            if (response.data.object.tid == t.tid) {
              count++;
            }
          });

          //이미 있으면 안함 없으면 추가
          if (count == 0) {
            try {
              this.tags.push(res);
            } catch {
              this.tags = [];
              this.tags.push(res);
            }
          }

          //showProject를 위함
          //this.selectedTags.push(response.data.object.tid);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addTagLink: function(tags, exid, tid, tagText) {
      axios
        .post(this.$SERVER_URL + `/experience/tag`, {
          exid: exid,
          tid: tid,
        })
        .then((response) => {
          var temp = { tid: tid, tagName: tagText, state: false };
          tags.push(temp);
        })
        .catch((error) => {
          console.log(error);
        });
    },

  },
};
</script>

<style>
.container {
  margin-top: 50px;
  width: 80%;
  padding: 0;
}
.box-table {
  border: 1px solid #888888;
  border-radius: 5px;
  margin-top: 50px;
  padding: 2%;
  background: #eeeeee;
  opacity: 0.8;

}
.noTag {
  text-align: right;
}
.img-circle {
    position: relative;
    background:#000000;
    
    border-radius: 50%;
    height: 200px;
    width: 200px;
    opacity: 0.6;
        background-size: cover;
      margin-left: auto;

  margin-right: auto;
      
    border: 3px solid #222222;
    box-shadow: 0 0 8px rgb(111, 111, 111);
}

.img-circle .content{
      width: 65%;
      height: 65%;
      text-align: center;
         position: relative;
         transform: translate(25%, 30%);
         font-size:15px;
         color: white;
         margin-top: auto;
         margin-bottom: auto;
    }

    .txt_line p{
        overflow: hidden;
       text-overflow: ellipsis; 
      width: 250px;

       white-space: normal;
      line-height: 1.2;
      max-height: 2.4em; 

      margin-left: auto;
      margin-right: auto;
      text-align: center;
    }

.icon-right {
  text-align: end;
  padding: 5%;
  margin-right: 10%;
}

.icon-right img {
  margin-right: 8%;
}

.col-cotents h2{
    text-align: center;
}


.date-align{
    text-align: right ;
}

.col-button-custom{
  margin-left: auto;
 margin-right: auto;
   
}

.col-button-custom {
  margin-left: auto;
  margin-right: auto;
}
.tag-custom {
  width: 30%;
  display: flex;
}

.txt_tag {
  display: flex;
  position: relative;
  margin: 16px 26px 0 0;
  font-size: 13px;
  vertical-align: top;

  white-space: nowrap;
}

.editor_tag {
  display: flex;
  width: 100%;
  min-height: 50px;
  margin: 0 auto;
  padding: 0 0 5px;
  box-sizing: border-box;
  font-size: 0;
}

.inp_tag {
  display: flex;
  margin: 16px 26px 0 0;
  font-size: 13px;
  color: #909090;
  vertical-align: top;
}

.inp_tag .tf_g {
  display: inline-block;
  margin: 0;
  border: 0;
  font-size: 13px;
  color: #333;
  vertical-align: top;
  outline: none;
  background: #eeeeee;
}

.txt_tag .tf_g {
  display: inline-block;
  margin: 0;
  border: 0;
  font-size: 13px;
  color: #333;
  vertical-align: top;
  outline: none;
  background: #eeeeee;
}
.tag-margin-custom {
  padding: 0.5%;
  margin: 5px;
}

.card-copy {
  position: relative;
  display: flex;
  flex-direction: column;
  min-width: 0;
  word-wrap: break-word;
  background-color: #ffffffaa;
  background-clip: border-box;
  border: 1px solid rgba(0, 0, 0, 0.125);
  border-radius: 5px;
  margin-bottom: 5%;
  /* box-shadow: 2px 2px 2px rgb(111, 111, 111); */
}
#experienceList {
  margin-top: 30px;
}

.temp1{
  display: none;
  margin: 0;
}
</style>
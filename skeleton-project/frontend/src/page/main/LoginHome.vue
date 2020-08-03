<template>
  <div class="text-align-left">
    <carousel-3d :width="400" :height="320">
      <div v-for="(num, i) in slidesLength" :key="i">
        <!-- 클릭하면 포트폴리오 디테일 페이지로 이동 -->
        <slide id="carousel_card" :index="i" @click="gotoDetail(pjtAll[i].pid)">
          <!-- v-bind:class="{ 'current' : isCurrent(i) }" -->
          <div class="m-3">
            <h2>
              <!-- 제목 -->
              <!-- <b-avatar variant="info" src="https://placekitten.com/300/300" class="mr-3"></b-avatar> -->
              <!-- 📁 -->
              <img
                class="Project-Image"
                src="https://cdn4.iconfinder.com/data/icons/macaron-1/48/file-manager-512.png"
                style="width: 5rem; height: 5rem;"
              />
              {{pjtAll[i].title}}
              <b-button size="sm" variant="outline-dark">download</b-button>
              <!-- <b-button size="sm" variant="outline-dark">상세보기</b-button> -->
              <!-- @click="gotopdetail"  -->
            </h2>

            <small>
              <!-- 날짜 -->
              {{pjtAll[i].start_date}} ~ {{pjtAll[i].end_date}}
            </small>
            <p class="mt-2">
              <!-- 내용 -->
              {{pjtAll[i].contents.slice(0,130)}}
            </p>
            <div>
              <h4>
                <!-- 태그 -->
                <div v-if="pjtAll[i].tag.length>0">
                  <b-badge
                    v-for="(ptag, j) in pjtAll[i].tag.slice(0,3)"
                    :key="j"
                    pill
                    class="mr-3"
                    id="tag"
                    text-variant="black"
                  >
                    <!-- id말고 tag_name으로 바꾸기 -->
                    {{ ptag.tagName }}
                  </b-badge>...
                </div>
                <div v-else>
                  <small>태그를 추가해보세요.</small>
                </div>
              </h4>
            </div>
          </div>
        </slide>
      </div>
    </carousel-3d>
  </div>
</template>

<script>
import { Carousel3d, Slide } from "vue-carousel-3d";
import axios from "axios";

export default {
  components: {
    Carousel3d,
    Slide,
  },
  data: () => {
    return {
      slidesLength: 1,
      pjtAll: [],
    };
  },
  created() {
    axios
      .get(this.$SERVER_URL + "/portfolio/all/", {
        params: { uid: localStorage["uid"] },
      })
      .then((response) => {
        console.log(response);
        // this.slidesLength = response.data.object.length;
        this.pjtAll = response.data.object;
        this.slidesLength = response.data.object;
        console.log(this.slidesLength);
        console.log(this.pjtAll);
      });
  },
  methods: {
    gotoDetail(pid) {
      this.$router.push({ path: "/PortfolioDetails", params: { pid: pid } });
    },
  },
};
</script>

<style>
#carousel_card {
  border: none;
  border-radius: 5px;
  padding: auto;
}
#carousel_card:hover {
  cursor: pointer;
}
/* #tag {
  background-color: #747474;
} */

.Project-Image {
  -webkit-filter: grayscale(100%);
}
.current .Project-Image {
  -webkit-filter: grayscale(0%);
}
.current {
  background-color: #ffcabd;
}
</style>
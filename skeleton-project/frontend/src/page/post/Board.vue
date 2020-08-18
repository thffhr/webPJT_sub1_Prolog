<template>
  <div
      :id="id"
      class="board"
      @dragover.prevent
      @drop.prevent="drop"
      >
    <slot />
  </div>
</template>

<script>
import EventBus from './EventBus';

export default {
  name: 'Board',
  props: ['id'],

  created(){
    this.msg
    EventBus.$on("cardToBoard_cardId", (payload) => {
     /*  this.msg = payload;
      this.delete(this.msg) } */}
      )
  }
  ,
  methods: {
    drop: e=>{
      const card_id = e.dataTransfer.getData('card_id');
      const parent_id = e.dataTransfer.getData('parent_id');

      const card = document.getElementById(card_id);

      card.style.display = "block";
      
      //ex) board-d-1
      //ex) card-s-e-384

      var myregexp2 = new RegExp("-"); 
      var start = parent_id.split(myregexp2)[1];
      var end = e.target.id.split(myregexp2)[1];
      var splited_bun = card_id.split(myregexp2)[2];
      var splited_idx = card_id.split(myregexp2)[3];  

  /* 
      alert("시험" + start);
      alert("시험" + end);
      alert("시험" + splited_bun);
      alert("시험" + splited_idx);

      alert("옮겨지는 보드의 아이디(에서)" + parent_id);
      alert("옮기는 보드 아이디(으로)" + e.target.id);
      alert("드래그 하는 카드 아이디" + card_id); */

      //같은 부모에 드래그앤 드랍하면 아무일도 없음..
      if(e.target.id == parent_id){
      
      }
      //만약에 같은 요소를 선택했으면
      else if(e.target.id == ""){
        
      }
      //나머지는 정상..?
      else{
        
         EventBus.$emit('test', "이벤트 실행 ㄱㄱ");
      }

      //e.target.createElement("div");
      e.target.appendChild(card);
      

      //s -> d는 추가
      //d -> s는 삭제
      EventBus.$emit('BoardToApply', start + "-" + end + "-" + splited_bun + "-" + splited_idx);
        
    },
  
  /* delete:function(cardId){
    
    alert("끝")
    const card = document.getElementById(cardId);
    card.parentNode.removeChild(cardId);

  } */
  }
}
</script>
<style>

</style>
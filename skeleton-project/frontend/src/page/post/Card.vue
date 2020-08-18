<template>
    <div
        :id="id"
        class="card"
        :draggable="draggable"
        @dragstart="dragStart"
        @dragover.stop
        
        @dragover.prevent
        @drop.prevent="drop"
        >
        <slot />
    </div>
</template>

<script>
import EventBus from './EventBus';


export default {
    name: 'Card',
    props: ['id', 'draggable'],
    methods:{
        dragStart: e => {
            const target = e.target;

            e.dataTransfer.setData('card_id', target.id);
           // alert("여기요" + target.id);

            if(target.parentNode.id == ""){
                e.dataTransfer.setData('parent_id',target.parentNode.parentNode.id);
            }
            else{
                e.dataTransfer.setData('parent_id',target.parentNode.id);
            }

            setTimeout(() => {
                target.style.display = "none";
            },0);

        },

          drop: e=>{
        const card_id = e.dataTransfer.getData('card_id');

        const card = document.getElementById(card_id);
        
        const parent = card.parentNode;
        
        //e.target.style.display = "none";
        //card.parentNode.append(card);
        //e.target.appendChild(card);
        //alert("안녕1"+card_id);

        EventBus.$emit('cardToBoard_cardId', card_id);
        
        }
    },
      
}
</script>
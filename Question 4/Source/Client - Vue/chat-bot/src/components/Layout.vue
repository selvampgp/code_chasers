<template>
    <div class="layout">
        <div class="column">
            <h3>{{ msg }}</h3>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Layout',
    props: {
        msg: String
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.layout {
    text-align: center;
}

h3 {
    font-size: 35px;
    line-height: 1em;
    font-weight: 700;
    color: white;
    display: inline-block;
    margin: 10px 86px;
}
</style>

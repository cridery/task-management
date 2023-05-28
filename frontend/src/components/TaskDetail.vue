<template>
    <div v-if="task">
        <h2>{{ task.title }}</h2>
        <p>{{ task.description }}</p>
        <p>Status: {{ task.status }}</p>
        <p>Due: {{ formatDueDate(task.dueDate) }}</p>
    </div>
</template>

<script>
export default {
    name: 'TaskDetail',
    props: {
        task: {
            type: Object,
            required: true,
            default: null
        }
    },
    methods: {
        formatDueDate(date) {
            return new Date(date).toLocaleDateString('en-US', { year: 'numeric', month: '2-digit', day: '2-digit' });
        }
    }
};
</script>

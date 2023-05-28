<template>
    <div v-if="task" class="space-y-2">
        <h2 class="text-2xl font-bold">{{ task.title }}</h2>
        <p class="pt-4 pb-8">{{ task.description }}</p>
        <div class="flex">
            <p class=" ">Status: </p>
            <p class="font-medium ml-1 "> {{ formatStatus(task.status) }}</p>
        </div>
        <div class="flex">
            <p>Due: </p>
            <p class="font-medium ml-1">{{ formatDueDate(task.dueDate) }}</p>
        </div>
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
        },
        formatStatus(status) {
            switch (status) {
                case 'TODO':
                    return 'To Do';
                case 'IN_PROGRESS':
                    return 'In Progress';
                case 'DONE':
                    return 'Done';
                default:
                    return 'Unknown';
            }
        }
    }
};
</script>

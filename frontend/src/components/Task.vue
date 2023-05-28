<template>
    <div class="task">
        <h2>
            {{ task.title }}
        </h2>
        <p>
            {{ task.description }}
        </p>
        <p>
            Status: {{ task.status }}
        </p>
        <p>
            Due: {{ task.dueDate }}
        </p>
    </div>
</template>

<script>
    export default {
        name: 'Task',
        props: {
            task: {
                type: Object,
                required: true
            }
        }
}
</script>
<template>
    <div class="w-full flex">
        <div class="w-1/3">
            <div v-for="task in tasks" :key="task.id" class="" @click="selectTask(task)">
                <h3 class="cursor-pointer">{{ task.title }}</h3>
                <p>{{ shortenDescription(task.description) }}</p>
            </div>
            <button @click="showTaskForm = true">Add new task</button>
        </div>
        <div class="w-2/3">
            <template v-if="selectedTask && !showTaskForm">
                <TaskDetail :task="selectedTask" @task-updated="updateTask" />
            </template>
            <template v-else>
                <TaskForm @task-added="fetchTasks" @task-cancelled="showTaskForm = false" />
            </template>
        </div>
    </div>
</template>

<script>
import TaskForm from "./TaskForm.vue";
import TaskDetail from "./TaskDetail.vue";


import { ref, onMounted } from "vue";
import { store } from "../store";

export default {
    components: {
        TaskDetail,
        TaskForm,
    },
    name: "TaskList",
    setup() {
        const tasks = ref([]);
        const selectedTask = ref(null);
        const showTaskForm = ref(false);

        const selectTask = (task) => {
            selectedTask.value = task;
            showTaskForm.value = false; // Hide the task form when a task is selected
        };

        const shortenDescription = (desc) => {
            return desc.length > 50 ? desc.substring(0, 47) + "..." : desc;
        };

        const fetchTasks = async () => {
            try {
                await store.dispatch("fetchTasks");
                tasks.value = store.state.tasks;
            } catch (error) {
                console.error(error);
            }
        };

        const updateTask = async (task) => {
            try {
                await store.dispatch("updateTask", task);
                selectedTask.value = null;
                fetchTasks();
            } catch (error) {
                console.error(error);
            }
        };

        onMounted(fetchTasks);

        return {
            tasks,
            selectedTask,
            showTaskForm,
            selectTask,
            shortenDescription,
        };
    },
};
</script>

<template>
    <div class="w-full flex">
        <div class="w-1/3">
            <div v-for="task in tasks" :key="task.id" class="" @click="selectTask(task)">
                <h3 class="cursor-pointer">{{ task.title }}</h3>
                <p>{{ shortenDescription(task.description) }}</p>
                <div>
                    <button @click.stop="enterEditMode(task.id)">
                        Edit
                    </button>
                    <button @click.stop="deleteTask(task.id)">
                       Delete
                    </button>
                </div>
            </div>
            <button @click="showTaskForm = true">Add new task</button>
        </div>
            <div class="w-2/3">
                <template v-if="selectedTask && !showTaskForm && !editModeTaskId">
                    <TaskDetail :task="selectedTask" />
                </template>
                <template v-else-if="editModeTaskId">
                    <TaskEdit :task="selectedTask" @task-updated="updateTask" @cancel-edit="exitEditMode" />
                </template>
                <template v-else-if="showTaskForm">
                    <TaskForm @task-added="fetchTasks" @task-cancelled="showTaskForm = false" />
                </template>
            </div>
    </div>
</template>

<script>
import TaskForm from "./TaskForm.vue";
import TaskDetail from "./TaskDetail.vue";
import TaskEdit from "./TaskEdit.vue";

import { ref, onMounted } from "vue";
import { store } from "../store";


export default {
    components: {
        TaskDetail,
        TaskForm,
        TaskEdit,
    },
    name: "TaskList",
    setup() {
        const tasks = ref([]);
        const selectedTask = ref(null);
        const showTaskForm = ref(false);
        const editModeTaskId = ref(null);

        const selectTask = (task) => {
            selectedTask.value = task;
            showTaskForm.value = false; // Hide the task form when a task is selected
            exitEditMode();
        };

        const enterEditMode = (taskId) => {
            const task = tasks.value.find((t) => t.id === taskId);
            if (task) {
                selectedTask.value = task;
            }
                editModeTaskId.value = taskId;

        };

        const exitEditMode = () => {
            editModeTaskId.value = null;
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
                exitEditMode();
                fetchTasks();
            } catch (error) {
                console.error(error);
            }
        };

        const deleteTask = async (taskId) => {
            try {
                await store.dispatch("deleteTask", taskId);
                selectedTask.value = null;
                exitEditMode();
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
            editModeTaskId,
            selectTask,
            enterEditMode,
            exitEditMode,
            shortenDescription,
            deleteTask,
            updateTask,
        };
    },
};
</script>

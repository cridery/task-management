<template>
    <div v-if="task">
        <form @submit.prevent="saveTask" class="space-y-4">
            <div>
                <!-- <label for="title">Title</label> -->
                <input type="text" 
                    id="title" 
                    v-model="updatedTask.title"
                    class="block w-full p-4 text-gray-900 border border-gray-300 rounded-lg bg-gray-50 sm:text-md focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    required 
                />
            </div>
            <div>
                <!-- <label for="description">Description</label> -->
                <textarea 
                    id="description"
                    v-model="updatedTask.description" 
                    class="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" 
                    required>
                </textarea>
            </div>
            <div>
                <label 
                    for="status"
                    class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >
                    Status:
                </label>
                <select id="status" 
                v-model="updatedTask.status" 
                required
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                    <option value="TODO">To Do</option>
                    <option value="IN_PROGRESS">In Progress</option>
                    <option value="DONE">Done</option>
                </select>
            </div>
            <div>
                <label for="dueDate" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Due Date:</label>
                <input type="date" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" id="dueDate" v-model="updatedTask.dueDate" required />
            </div>
            <div class="flex w-full justify-center items-center">
                <button 
                    type="submit"
                    class="py-2.5 px-5 mr-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"
                >
                    Save
                </button>
            </div>
        </form>
    </div>
</template>

<script>
export default {
    name: 'TaskEdit',
    props: {
        task: {
            type: Object,
            required: true,
            default: null
        }
    },
    watch: {
        task(newTask) {
            this.updatedTask = { ...newTask, dueDate: new Date(newTask.dueDate).toISOString().split('T')[0] };
        }
    },
    data() {
        return {
            updatedTask: { ...this.task, dueDate: new Date(this.task.dueDate).toISOString().split('T')[0] }
        };
    },
    methods: {
        saveTask() {
            // Emit the 'task-updated' event with the updated task data
            this.$emit('task-updated', this.updatedTask);
        }
    }
};

</script>

import { Component, OnInit } from '@angular/core';
import List from 'src/app/models/list';
import Task from 'src/app/models/task';
import {TaskService} from 'src/app/task.service';
import { ActivatedRoute, Router, Params } from '@angular/router';

@Component({
  selector: 'app-task-view',
  templateUrl: './task-view.component.html',
  styleUrls: ['./task-view.component.scss']
})
export class TaskViewComponent implements OnInit {
  lists: List[] = [];
  tasks: Task[] = [];
  listId: string;

  constructor(
    private taskService: TaskService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.listId = params.listId;
      if(!this.listId)
        this.tasks = undefined;
      else
        this.taskService.getTasks(this.listId).subscribe((tasks: Task[]) => this.tasks = tasks);
    });

    this.taskService.getLists()
      .subscribe((lists: List[]) => this.lists = lists);
  }

  onTaskClick(task: Task){
    // We want to set the task to completed.
    this.taskService.setCompleted(this.listId, task).subscribe(()=> {
      console.log("Completed Successfully!");
      task.completed = !task.completed;
    })
  }

  deleteTask(task: Task){
    this.taskService.deleteTask(this.listId, task._id)
      .subscribe((task: Task) => this.tasks = this.tasks.filter(t => t._id != task._id));
  }

  deleteList(list: List){
    this.taskService.deleteList(list._id)
      .subscribe(() => this.lists = this.lists.filter(l => l._id != list._id));
  }

  onDeleteListClick() {
    this.taskService.deleteList(this.listId).subscribe((res: any) =>{
      this.router.navigate(['/lists']);
      console.log(res);
    })
  }

  onDeleteTaskClick(id: string) {
    this.taskService.deleteTask(this.listId, id).subscribe((res: any) =>{
      this.tasks = this.tasks.filter(val => val._id !== id);
      //this.router.navigate(['/lists']);
      console.log(res);
    })
  }

  addTaskClick(){
    if(!this.listId){
      alert("Please select a list to add tasks to.");
      return;
    }
    this.router.navigate(['./new-task'], {relativeTo: this.route});
  }
}

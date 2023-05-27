import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'employee',
        data: { pageTitle: 'Employee list' },
        loadChildren: () => import('./employee/employee.module').then(m => m.EmployeeModule),
      },
    ]),
  ],
})
export class EntityRoutingModule {}

export interface IEmployee {
  id?: number;
  name?: string;
  document?: number;
  salary?: number;
  otherNames?: string | null;
  annualSalary?: number | null;
}

export class Employee implements IEmployee {
  constructor(
    public id?: number,
    public name?: string,
    public document?: number,
    public salary?: number,
    public otherNames?: string | null,
    public annualSalary?: number | null
  ) {}
}

export function getEmployeeIdentifier(employee: IEmployee): number | undefined {
  return employee.id;
}

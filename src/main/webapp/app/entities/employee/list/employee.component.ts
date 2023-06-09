import { Component, OnInit } from "@angular/core";
import { HttpHeaders, HttpResponse } from "@angular/common/http";
import { ActivatedRoute, Router } from "@angular/router";
import { combineLatest } from "rxjs";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";

import { IEmployee } from "../employee.model";

import { ASC, DESC, ITEMS_PER_PAGE, SORT } from "app/config/pagination.constants";
import { EmployeeService } from "../service/employee.service";

@Component({
  selector: "jhi-employee",
  templateUrl: "./employee.component.html"
})
export class EmployeeComponent implements OnInit {
  employees?: IEmployee[];
  isLoading = false;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page?: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  searchId?: number;

  constructor(
    protected employeeService: EmployeeService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected modalService: NgbModal
  ) {}

  performSearch(): void {
    if (this.searchId) {
      this.loadPage(undefined, undefined, this.searchId);
    }
  }
  loadPage(page?: number, dontNavigate?: boolean, searchId?: number): void {
    this.isLoading = true;
    const pageToLoad: number = page ?? this.page ?? 1;

    const params: any = {
      page: pageToLoad - 1,
      size: this.itemsPerPage,
      sort: this.sort()
    };

    if (typeof searchId === "number") {
      params.id = searchId;
    }

    this.employeeService
      .query(params)
      .subscribe(
        (res: HttpResponse<IEmployee[]>) => {
          this.isLoading = false;
          this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate);
        },
        () => {
          this.isLoading = false;
          this.onError();
        }
      );
  }

  ngOnInit(): void {
    this.handleNavigation();
  }

  trackId(index: number, item: IEmployee): number {
    return item.id!;
  }


  protected sort(): string[] {
    const result = [this.predicate + "," + (this.ascending ? ASC : DESC)];
    if (this.predicate !== "id") {
      result.push("id");
    }
    return result;
  }

  protected handleNavigation(): void {
    combineLatest([this.activatedRoute.data, this.activatedRoute.queryParamMap]).subscribe(([data, params]) => {
      const page = params.get("page");
      const pageNumber = page !== null ? +page : 1;
      const sort = (params.get(SORT) ?? data["defaultSort"]).split(",");
      const predicate = sort[0];
      const ascending = sort[1] === ASC;
      if (pageNumber !== this.page || predicate !== this.predicate || ascending !== this.ascending) {
        this.predicate = predicate;
        this.ascending = ascending;
        this.loadPage(pageNumber, true);
      }
    });
  }

  protected onSuccess(data: IEmployee[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get("X-Total-Count"));
    this.page = page;
    if (navigate) {
      this.router.navigate(["/employee"], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + "," + (this.ascending ? ASC : DESC)
        }
      });
    }
    this.employees = data ?? [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }
}

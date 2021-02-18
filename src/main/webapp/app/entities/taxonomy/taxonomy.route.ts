import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITaxonomy, Taxonomy } from 'app/shared/model/taxonomy.model';
import { TaxonomyService } from './taxonomy.service';
import { TaxonomyComponent } from './taxonomy.component';
import { TaxonomyDetailComponent } from './taxonomy-detail.component';
import { TaxonomyUpdateComponent } from './taxonomy-update.component';

@Injectable({ providedIn: 'root' })
export class TaxonomyResolve implements Resolve<ITaxonomy> {
  constructor(private service: TaxonomyService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITaxonomy> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((taxonomy: HttpResponse<Taxonomy>) => {
          if (taxonomy.body) {
            return of(taxonomy.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Taxonomy());
  }
}

export const taxonomyRoute: Routes = [
  {
    path: '',
    component: TaxonomyComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.taxonomy.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TaxonomyDetailComponent,
    resolve: {
      taxonomy: TaxonomyResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.taxonomy.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TaxonomyUpdateComponent,
    resolve: {
      taxonomy: TaxonomyResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.taxonomy.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TaxonomyUpdateComponent,
    resolve: {
      taxonomy: TaxonomyResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.taxonomy.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICollectionData, CollectionData } from 'app/shared/model/collection-data.model';
import { CollectionDataService } from './collection-data.service';
import { CollectionDataComponent } from './collection-data.component';
import { CollectionDataDetailComponent } from './collection-data-detail.component';
import { CollectionDataUpdateComponent } from './collection-data-update.component';

@Injectable({ providedIn: 'root' })
export class CollectionDataResolve implements Resolve<ICollectionData> {
  constructor(private service: CollectionDataService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICollectionData> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((collectionData: HttpResponse<CollectionData>) => {
          if (collectionData.body) {
            return of(collectionData.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CollectionData());
  }
}

export const collectionDataRoute: Routes = [
  {
    path: '',
    component: CollectionDataComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.collectionData.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CollectionDataDetailComponent,
    resolve: {
      collectionData: CollectionDataResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.collectionData.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CollectionDataUpdateComponent,
    resolve: {
      collectionData: CollectionDataResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.collectionData.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CollectionDataUpdateComponent,
    resolve: {
      collectionData: CollectionDataResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bioBaseApplicationApp.collectionData.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICollectionData } from 'app/shared/model/collection-data.model';

type EntityResponseType = HttpResponse<ICollectionData>;
type EntityArrayResponseType = HttpResponse<ICollectionData[]>;

@Injectable({ providedIn: 'root' })
export class CollectionDataService {
  public resourceUrl = SERVER_API_URL + 'api/collection-data';

  constructor(protected http: HttpClient) {}

  create(collectionData: ICollectionData): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(collectionData);
    return this.http
      .post<ICollectionData>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(collectionData: ICollectionData): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(collectionData);
    return this.http
      .put<ICollectionData>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICollectionData>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICollectionData[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(collectionData: ICollectionData): ICollectionData {
    const copy: ICollectionData = Object.assign({}, collectionData, {
      collectionDate:
        collectionData.collectionDate && collectionData.collectionDate.isValid()
          ? collectionData.collectionDate.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.collectionDate = res.body.collectionDate ? moment(res.body.collectionDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((collectionData: ICollectionData) => {
        collectionData.collectionDate = collectionData.collectionDate ? moment(collectionData.collectionDate) : undefined;
      });
    }
    return res;
  }
}

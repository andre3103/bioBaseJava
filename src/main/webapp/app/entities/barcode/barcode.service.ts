import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBarcode } from 'app/shared/model/barcode.model';

type EntityResponseType = HttpResponse<IBarcode>;
type EntityArrayResponseType = HttpResponse<IBarcode[]>;

@Injectable({ providedIn: 'root' })
export class BarcodeService {
  public resourceUrl = SERVER_API_URL + 'api/barcodes';

  constructor(protected http: HttpClient) {}

  create(barcode: IBarcode): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(barcode);
    return this.http
      .post<IBarcode>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(barcode: IBarcode): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(barcode);
    return this.http
      .put<IBarcode>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBarcode>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBarcode[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(barcode: IBarcode): IBarcode {
    const copy: IBarcode = Object.assign({}, barcode, {
      data: barcode.data && barcode.data.isValid() ? barcode.data.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.data = res.body.data ? moment(res.body.data) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((barcode: IBarcode) => {
        barcode.data = barcode.data ? moment(barcode.data) : undefined;
      });
    }
    return res;
  }
}

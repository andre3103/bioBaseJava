import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVoucherInfo } from 'app/shared/model/voucher-info.model';

type EntityResponseType = HttpResponse<IVoucherInfo>;
type EntityArrayResponseType = HttpResponse<IVoucherInfo[]>;

@Injectable({ providedIn: 'root' })
export class VoucherInfoService {
  public resourceUrl = SERVER_API_URL + 'api/voucher-infos';

  constructor(protected http: HttpClient) {}

  create(voucherInfo: IVoucherInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(voucherInfo);
    return this.http
      .post<IVoucherInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(voucherInfo: IVoucherInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(voucherInfo);
    return this.http
      .put<IVoucherInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVoucherInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVoucherInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(voucherInfo: IVoucherInfo): IVoucherInfo {
    const copy: IVoucherInfo = Object.assign({}, voucherInfo, {
      data: voucherInfo.data && voucherInfo.data.isValid() ? voucherInfo.data.format(DATE_FORMAT) : undefined,
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
      res.body.forEach((voucherInfo: IVoucherInfo) => {
        voucherInfo.data = voucherInfo.data ? moment(voucherInfo.data) : undefined;
      });
    }
    return res;
  }
}

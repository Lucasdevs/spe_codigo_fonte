import { HttpParams } from '@angular/common/http';
import { Table } from 'primeng/primeng';

export class RequestUtil {

    static PAGE_SIZE_DEFAULT = 10;

    static getRequestParamsTable = (datatable: Table): HttpParams => {
        let params: HttpParams = new HttpParams();
        if (datatable == null) {
            params = params.append('size', RequestUtil.PAGE_SIZE_DEFAULT.toString());
            return params;
        }

        params = params.append('page', Math.round(datatable.first / datatable.rows).toString());
        params = params.append('size', datatable.rows == null ? RequestUtil.PAGE_SIZE_DEFAULT.toString() : datatable.rows.toString());

        const direction = datatable.sortOrder === 1 ? 'ASC' : 'DESC';
        params = params.append('sort', datatable.sortField == null ? '' : datatable.sortField + ',' + direction);

        return params;
    }
}

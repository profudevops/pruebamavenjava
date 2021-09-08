package com.mx.profuturo.bolsa.service.charts;

import com.mx.profuturo.bolsa.model.graphics.adapters.AreaChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.BarChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.DonutChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.adapters.StackedChartAdapter;
import com.mx.profuturo.bolsa.model.graphics.vo.BarChartWidgetVO;
import com.mx.profuturo.bolsa.model.graphics.vo.SingleKPIWidgetVO;
import com.mx.profuturo.bolsa.service.desk.KPIEscritorio;

public interface ChartAdapterService {

	BarChartWidgetVO getBarChartWidget(BarChartAdapter adapter);

	Object getStackedChartWidget(StackedChartAdapter adapter);

	Object getDonutChartWidget(DonutChartAdapter adapter);


	Object getAreaChartWidget(AreaChartAdapter adapter);

	Object getLineChartWidget(AreaChartAdapter adapter);

	SingleKPIWidgetVO translateKPI(KPIEscritorio entrevistasHoy);

}

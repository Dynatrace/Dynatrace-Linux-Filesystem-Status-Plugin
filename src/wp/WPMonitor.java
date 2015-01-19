package wp;

import java.util.Collection;

import com.dynatrace.diagnostics.pdk.Monitor;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.pdk.MonitorMeasure;
import com.dynatrace.diagnostics.pdk.Status;

public class WPMonitor extends LinuxFilesystemMonitor implements Monitor {

	private static final String METRIC_GROUP = "Linux Filesystem Status Monitor";
	private static final String MSR_PERCENT_USAGE = "PercentUsage";
	
	@Override
	public Status setup(MonitorEnvironment env) throws Exception {
		return super.setup(env);
	}

	@Override
	public Status execute(MonitorEnvironment env) throws Exception {
		Status result = super.execute(env);

		Collection<MonitorMeasure> measures;
		if ((measures = env.getMonitorMeasures(METRIC_GROUP, MSR_PERCENT_USAGE)) != null) {
			for (MonitorMeasure measure : measures)
				measure.setValue(returnPercentUsage());
		}

		return result;
	}

	@Override
	public void teardown(MonitorEnvironment env) throws Exception {
		super.teardown(env);
	}

}

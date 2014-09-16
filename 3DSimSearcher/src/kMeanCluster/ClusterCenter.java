package kMeanCluster;

import map.MappedMolecule;

public interface ClusterCenter {
	public double getDistanceFrom(MappedMolecule obj, String metric_desc);
	public void updateTotalWith(MappedMolecule obj);
        public void mergeWith(ClusterCenter center);
	public void averageValues();
        @Override
        public String toString();
}

---
layout: post
title:  "One Paper Has Been Accepted to SCA 2018"
date:   2018-02-07
categories: publications
---

## On the Performance of Spark on HPC Systems: Towards a Complete Picture

*Orcun Yildiz and Shadi Ibrahim*

ig Data analytics frameworks (e.g., Hadoop and Spark) have been increasingly used by many companies and research
labs to facilitate large-scale data analysis. However, with the growing needs of users and size of data, commodity- based
infrastructure will strain under the heavy weight of Big Data. On the other hand, HPC systems offer a rich set of 
opportunities for Big Data processing. As first steps toward Big Data processing on HPC systems, several research efforts 
have been devoted to understanding the performance of Big Data applications on these systems. Yet the HPC specific 
performance considerations have not been fully investigated. In this work, we conduct an experimental campaign to 
provide a clearer understanding of the performance of Spark, the de facto in-memory data processing framework, on HPC 
systems. We ran Spark using representative Big Data workloads on Grid?5000 testbed to evaluate how the latency, 
contention and file system?s configuration can influence the application performance. We discuss the implications of our 
findings and draw attention to new ways (e.g., burst buffers) to improve the performance of Spark on HPC systems.
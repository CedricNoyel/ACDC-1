---
layout: post
title:  "One Paper Has Been Accepted to CCGrid 2018"
date:   2018-01-30
categories: publications
---

## Nitro: Network-Aware Virtual Machine Images Management in Geo-Distributed Clouds

*Jad Darrous, Shadi Ibrahim, Amelie Chi Zhou and Christian Perez*

Recently, most large cloud providers, like Amazon and Microsoft, replicate their Virtual Machine Images (VMIs) 
on multiple geo-graphically distributed data centers to offer fast service provisioning. Provisioning a service may 
require to transfer a VMI over the wide-area network and there- fore is dictated by the distribution of VMIs and 
the network bandwidth in-between sites. Nevertheless, existing methods to facilitate VMIs management (i.e., 
retrieving VMIs) overlook network heterogeneity in geo-distributed clouds. In this paper, we design, implement 
and evaluate Nitro, a novel VMI management system that helps to minimize the transfer time of VMIs over a 
heterogeneous WAN. To achieve this goal, Nitro incorporates two complementary features. First, it makes use 
of deduplication to reduce the amount of data which will be transferred due to the high similarities within an image 
and in-between images. Second, Nitro is equipped with a network-aware data transfer strategy to effectively exploit 
links with high bandwidth when acquiring data and thus expedites the provisioning time. Our results show that the
 network-aware data transfer strategy offers optimal solution when acquiring VMIs while introducing minimal overhead. 
 Moreover, Nitro outperforms state-of-the-art VMI storage systems (e.g., Openstack Swift) by up to 77%.
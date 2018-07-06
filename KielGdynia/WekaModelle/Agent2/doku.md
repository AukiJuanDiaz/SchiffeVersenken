###################################################
############### Latitude ########################## -> lat_M5P500.model
###################################################

IBK, K = 1

=== Summary ===

Correlation coefficient                  0.3494
Mean absolute error                      0.0298
Root mean squared error                  0.0944
Relative absolute error                 86.6095 %
Root relative squared error             95.6685 %
Total Number of Instances            35949

IBK, K = 100


=== Summary ===

Correlation coefficient                  0.3827
Mean absolute error                      0.0292
Root mean squared error                  0.0919
Relative absolute error                 84.9205 %
Root relative squared error             93.1801 %
Total Number of Instances            35949     

##################################################################################

M5P 4.0

=== Summary ===

Correlation coefficient                  0.5929
Mean absolute error                      0.0261
Root mean squared error                  0.0803
Relative absolute error                 75.9431 %
Root relative squared error             81.3349 %
Total Number of Instances            35949     

M5P 50.0
lat_M5P500.model

=== Summary ===

Correlation coefficient                  0.5921
Mean absolute error                      0.0262
Root mean squared error                  0.0803
Relative absolute error                 76.181  %
Root relative squared error             81.4007 %
Total Number of Instances            35949


###################################################
############### RTT #### ########################## --> rtt_IBK100.model
###################################################

IBK, K = 100
rtt_IBK100.model

=== Summary ===

Correlation coefficient                  0.9956
Mean absolute error                      8.2517
Root mean squared error                 14.7591
Relative absolute error                  6.8024 %
Root relative squared error             10.1832 %
Total Number of Instances            35949    

#############################################################

linear Regression
rtt_LinReg.model

=== Summary ===

Correlation coefficient                  0.9723
Mean absolute error                     25.2595
Root mean squared error                 33.8874
Relative absolute error                 20.8229 %
Root relative squared error             23.381  %
Total Number of Instances            35949   

###################################################
############### SOG ############################### --> SOG_M5P40.model
###################################################

IBK, K = 1
SOG_IBK1.model

=== Summary ===

Correlation coefficient                  0.6034
Mean absolute error                      2.1666
Root mean squared error                  2.7659
Relative absolute error                 95.8878 %
Root relative squared error             96.7058 %
Total Number of Instances            35949

IBK, K = 100
SOG_IBK100.model

=== Summary ===

Correlation coefficient                  0.6418
Mean absolute error                      1.908
Root mean squared error                  2.3892
Relative absolute error                 84.4434 %
Root relative squared error             83.5371 %
Total Number of Instances            35949

######################################

M5P 40
SOG_M5P40.model

=== Summary ===

Correlation coefficient                  0.9267
Mean absolute error                      0.7607
Root mean squared error                  1.0748
Relative absolute error                 33.6669 %
Root relative squared error             37.5791 %
Total Number of Instances            35949

M5P 50
SOG_M5P500.model

=== Summary ===

Correlation coefficient                  0.9254
Mean absolute error                      0.7651
Root mean squared error                  1.0838
Relative absolute error                 33.861  %
Root relative squared error             37.8955 %
Total Number of Instances            35949    

SOG_M5P 100.0
SOG_M5P1000.model


=== Summary ===

Correlation coefficient                  0.9278
Mean absolute error                      0.7552
Root mean squared error                  1.0707
Relative absolute error                 33.4221 %
Root relative squared error             37.4376 %
Total Number of Instances            35949    


#################################################################
################ COG ############################################# --> COG_IBK100.model
##################################################################


IBK, K = 1
COG_IBK1.model

=== Summary ===

Correlation coefficient                  0.2764
Mean absolute error                      2.7683
Root mean squared error                  5.3292
Relative absolute error                159.753  %
Root relative squared error            112.2027 %
Total Number of Instances            35949     




IBK, K = 100
COG_IBK100.model

=== Summary ===

Correlation coefficient                  0.3077
Mean absolute error                      2.5188
Root mean squared error                  4.8625
Relative absolute error                145.353  %
Root relative squared error            102.3757 %
Total Number of Instances            35949  


##############################################################
M5P 4.0
COG_M5P40.model

=== Summary ===

Correlation coefficient                  0.0278
Mean absolute error                      2.6949
Root mean squared error                  6.0003
Relative absolute error                155.5175 %
Root relative squared error            126.3312 %
Total Number of Instances            35949    

M5P 50.0
COG_M5P500.model

=== Summary ===

Correlation coefficient                  0.0174
Mean absolute error                      2.7101
Root mean squared error                  6.0305
Relative absolute error                156.3926 %
Root relative squared error            126.968  %
Total Number of Instances            35949     


M5P 100
COG_M5P1000

=== Summary ===

Correlation coefficient                  0.0219
Mean absolute error                      2.7126
Root mean squared error                  6.0322
Relative absolute error                156.5385 %
Root relative squared error            127.0034 %
Total Number of Instances            35949     
